package com.child.profile.filters;


import com.child.profile.data.Child;
import com.child.profile.data.Parent;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.Priority;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
@Priority(1)
public class PersonValidationFilter implements Filter {
    List<String> blockedUrls = List.of("/profile/download");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest((HttpServletRequest) request);
        if (!blockedUrls.contains(cachedBodyHttpServletRequest.getRequestURI())) {
            String body = new BufferedReader(cachedBodyHttpServletRequest.getReader())
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            JsonMapper mapper = new JsonMapper();

            List<Parent> parents = mapper.readValue(body, new TypeReference<List<Parent>>() {
            });

            if (checkParentFields(parents)) {
                response.getWriter().write("Parent data is not valid");
                return;
            }
            for (Parent parent : parents) {
                if (!(checkChildren(parent.getChildren()))) {
                    response.getWriter().write("Child data is not valid");
                    return;
                }
            }
        }
        chain.doFilter(cachedBodyHttpServletRequest, response);
    }

    private boolean checkChildren(List<Child> children) {
        if (!(checkChildrenAge(children))) {
            return false;
        }

        return children.stream().allMatch(child ->
                ageChecker(child) &&
                children.size() >= 3 &&
                child.getName() != null &&
                !child.getName().isEmpty() &&
                child.getId() != null &&
                child.getId() > 0 &&
                child.getParent1() != null &&
                !child.getParent1().isEmpty() &&
                child.getParent2() != null &&
                !child.getParent2().isEmpty()
        );
    }

    private boolean checkParentFields(List<Parent> parents) {
        return parents.stream().allMatch(parent ->
            parent.getChildren().size() < 3 &&
            parent.getBirthDate() != null
            && (parent.getName() != null || !parent.getName().isEmpty())
            && (parent.getId() != null || parent.getId() >= 0)
            && (parent.getPartner() != null || !parent.getPartner().isEmpty())
        );
    }

    private boolean checkChildrenAge(List<Child> children) {
        AtomicBoolean checkedAge = new AtomicBoolean(false);
        for (Child child : children) {
                if (ageChecker(child)) {
                    break;
                }
        }
        return checkedAge.get();
    }

    private boolean ageChecker(Child child) {
        if (child.getBirthDate() != null) {
            long ageInMillis = System.currentTimeMillis() - child.getBirthDate().getTime();
            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            return ageInYears < 18;
        }
        return false;
    }
   @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
