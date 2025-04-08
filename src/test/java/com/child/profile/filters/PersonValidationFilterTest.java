package com.child.profile.filters;

import com.child.profile.ParentMockCreator;
import com.child.profile.data.Parent;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

class PersonValidationFilterTest {
    private final PersonValidationFilter personValidationFilter = new PersonValidationFilter();
    @Test
    public void testRetrieveChildProfileFilterNo3Children() throws Exception {

        JsonMapper jsonMapper = new JsonMapper();
        List<Parent> parentMockNo3Children = ParentMockCreator.createParentMockNo3Children();
        String content = jsonMapper.writeValueAsString(parentMockNo3Children);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent(content.getBytes());
        MockHttpServletResponse mockMvcResponse = new MockHttpServletResponse();
        personValidationFilter.doFilter(request, mockMvcResponse, null);
        Assertions.assertEquals("Parent data is not valid",mockMvcResponse.getContentAsString());
    }

    @Test
    public void testRetrieveChildProfileFilterNoChildUnder18() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();
        List<Parent> parentMockNo18 = ParentMockCreator.createParentNo18Mock();
        String content = jsonMapper.writeValueAsString(parentMockNo18);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent(content.getBytes());
        request.setContextPath("/download");
        MockHttpServletResponse mockMvcResponse = new MockHttpServletResponse();
        personValidationFilter.doFilter(request, mockMvcResponse, null);
        Assertions.assertEquals("Child data is not valid",mockMvcResponse.getContentAsString());
    }

}