package com.lambdaschool.usermodel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.usermodel.models.*;
import com.lambdaschool.usermodel.services.EatzService;
import com.lambdaschool.usermodel.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin",
        roles = {"USER", "ADMIN"})
public class EatzControllerUnitTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EatzService eatzService;

    private List<User> userList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        r1.setRoleid(1);
        Role r2 = new Role("user");
        r2.setRoleid(2);
        Role r3 = new Role("data");
        r3.setRoleid(3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                r1));
        admins.add(new UserRoles(new User(),
                r2));
        admins.add(new UserRoles(new User(),
                r3));
        User u1 = new User("admin",
                "ILuvM4th!",
                "admin@lambdaschool.local",
                admins);

        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@email.local"));
        u1.getUseremails()
                .get(0)
                .setUseremailid(10);

        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@mymail.local"));
        u1.getUseremails()
                .get(1)
                .setUseremailid(11);

        List<Eatz> listEatz = new ArrayList<Eatz>();
        Eatz eatz1 = new Eatz("title1",1,1,1);
        eatz1.setUser(u1);
        eatz1.setEatzid(100);
        Eatz eatz2 = new Eatz("title2",2,2,2);
        eatz2.setUser(u1);
        eatz2.setEatzid(200);
        Eatz eatz3 = new Eatz("title3",3,3,3);
        eatz3.setEatzid(300);
        eatz3.setUser(u1);
        listEatz.add(eatz1);
        listEatz.add(eatz2);
        listEatz.add(eatz3);
        u1.setUsereatz(listEatz);
        u1.setUserid(101);
        userList.add(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                r3));
        datas.add(new UserRoles(new User(),
                r2));
        User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local",
                datas);

        u2.getUseremails()
                .add(new Useremail(u2,
                        "cinnamon@mymail.local"));
        u2.getUseremails()
                .get(0)
                .setUseremailid(20);

        u2.getUseremails()
                .add(new Useremail(u2,
                        "hops@mymail.local"));
        u2.getUseremails()
                .get(1)
                .setUseremailid(21);

        u2.getUseremails()
                .add(new Useremail(u2,
                        "bunny@email.local"));
        u2.getUseremails()
                .get(2)
                .setUseremailid(22);

        u2.setUserid(102);
        userList.add(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r1));
        User u3 = new User("testingbarn",
                "ILuvM4th!",
                "testingbarn@school.lambda",
                users);

        u3.getUseremails()
                .add(new Useremail(u3,
                        "barnbarn@email.local"));
        u3.getUseremails()
                .get(0)
                .setUseremailid(30);

        u3.setUserid(103);
        userList.add(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u4 = new User("testingcat",
                "password",
                "testingcat@school.lambda",
                users);
        u4.setUserid(104);
        userList.add(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u5 = new User("testingdog",
                "password",
                "testingdog@school.lambda",
                users);
        u5.setUserid(105);
        userList.add(u5);

        System.out.println("\n*** Seed Data ***");
        for (User u : userList)
        {
            System.out.println(u);
        }
        System.out.println("*** Seed Data ***\n");

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    /*

    public ResponseEntity<?> listAllEatz(
            Authentication authentication)


*/
    @Test
    public void listAllEatz() throws Exception
    {
        String apiUrl = "/eatz/alleatzforuser";

        Mockito.when(userService.findByName("admin"))
                .thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0).getUsereatz());

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List",
                er,
                tr);
    }

    @Test
    public void listReallyAllEatz() throws Exception
    {
        String apiUrl = "/eatz/alleatzforuser";

        Mockito.when(userService.findByName("admin"))
                .thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb)
                .andReturn();
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0).getUsereatz());

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List",
                er,
                tr);
    }

/*
    public ResponseEntity<?> getEatzById(
            @PathVariable
                    Long eatzId)
                    */
@Test
public void getEatzById() throws Exception
{
    String apiUrl = "/eatz/id/200";

    Mockito.when(eatzService.findEatzById(200))
            .thenReturn(userList.get(0).getUsereatz().get(1));

    RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
            .accept(MediaType.APPLICATION_JSON);
    MvcResult r = mockMvc.perform(rb)
            .andReturn(); //
    String tr = r.getResponse()
            .getContentAsString();

    ObjectMapper mapper = new ObjectMapper();
    String er = mapper.writeValueAsString(userList.get(0).getUsereatz().get(1));

    System.out.println("Expect: " + er);
    System.out.println("Actual: " + tr);

    assertEquals("Rest API Returns List",
            er,
            tr);
}
                    /*
    public ResponseEntity<?> getEatzByName(
            @PathVariable
                    String title)

       public ResponseEntity<?> updateEatz(
            @RequestBody
                    Eatz updateEatz,
            @PathVariable
                    long eatzid)

      public ResponseEntity<?> addNewEatz(@Valid
                                              @RequestBody
                                                      Eatz newEatz,
                                        Authentication authentication) throws URISyntaxException

          public ResponseEntity<?> deleteEatzById(
            @PathVariable
                    long eatzid)

* */
}
