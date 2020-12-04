package com.example.UserService.controller;

import com.example.UserService.constants.UserServiceConstants;
import com.example.UserService.dao.Address;
import com.example.UserService.dao.Gender;
import com.example.UserService.dao.Title;
import com.example.UserService.dao.User;
import com.example.UserService.rest.UserController;
import com.example.UserService.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    User mockUser = getUser();


    @Test
    public void getUserDetails() throws Exception {
        final String CREATE_USER_URL = "/user/" + "10";
        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CREATE_USER_URL).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\n" +
                "    \"id\": \"3822\",\n" +
                "    \"title\": \"Master\",\n" +
                "    \"firstName\": \"John3822\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"empID\": \"emp_3822\",\n" +
                "    \"address\": {\n" +
                "        \"id\": \"3822\",\n" +
                "        \"street\": \"LANE STREET\",\n" +
                "        \"city\": \"SYDNEY\",\n" +
                "        \"state\": \"NSW\",\n" +
                "        \"postCode\": \"2000\"\n" +
                "    }\n" +
                "}";

        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void addUser() throws Exception {
        Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(mockUser);
        String examplePayload = "{\n" +
                "    \"id\": \"3822\",\n" +
                "    \"title\": \"Master\",\n" +
                "    \"firstName\": \"John3822\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"empID\": \"emp_3822\",\n" +
                "    \"address\": {\n" +
                "        \"id\": \"3822\",\n" +
                "        \"street\": \"LANE STREET\",\n" +
                "        \"city\": \"SYDNEY\",\n" +
                "        \"state\": \"NSW\",\n" +
                "        \"postCode\": \"2000\"\n" +
                "    }\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/addUser")
                .accept(MediaType.APPLICATION_JSON)
                .content(examplePayload)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(examplePayload, mvcResult.getResponse().getContentAsString(), false);
    }

    private User getUser() {
        String userID = String.valueOf(3822);
        Address address = new Address(userID, UserServiceConstants.STREET, UserServiceConstants.CITY,
                UserServiceConstants.STATE, UserServiceConstants.POST_CODE);
        User user = new User(userID, Title.Master, UserServiceConstants.FIRST_NAME.concat(userID), UserServiceConstants.LAST_NAME, Gender.Male, UserServiceConstants.EMPLOYEE_ID.concat(userID));
        user.setAddress(address);
        return user;
    }
}
