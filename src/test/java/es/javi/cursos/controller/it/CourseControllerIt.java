package es.javi.cursos.controller.it;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.javi.cursos.model.Subject;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class CourseControllerIt {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MvcResult result;

    @Before
    public void beforeScenario() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /************************************************************************************************* GET ALL COURSES*/
    @When("^the course client calls courses endpoint /courses by page of (\\d+) and size of (\\d+)$")
    public void the_course_client_calls_courses_endpoint_courses_by_page_of_and_size_of(int page, int size) throws Exception {
        result = mvc.perform(get("/courses").param("page", ""+page).param("size", ""+size)).andDo(print()).andReturn();
    }

    @Then("^the course client receives \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" courses$")
    public void the_course_client_receives_and_courses(String course1, String course2, String course3, String course4, String course5) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(course1);
        assertThat(responseContent).contains(course2);
        assertThat(responseContent).contains(course3);
        assertThat(responseContent).contains(course4);
        assertThat(responseContent).contains(course5);
    }

    /********************************************************************************************* GET COURSE SUBJECTS*/
    @When("^the course client calls courses/subjects endpoint /courses/(\\d+)/subjects by page of (\\d+) and size of (\\d+)$")
    public void the_course_client_calls_courses_subjects_endpoint_courses_subjects_by_page_of_and_size_of(int courseId, int page, int size) throws Exception {
        result = mvc.perform(get("/courses/"+courseId+"/subjects").param("page", ""+page).param("size", ""+size)).andDo(print()).andReturn();
    }

    @Then("^the course client receives \"([^\"]*)\", and \"([^\"]*)\" subjects$")
    public void the_course_client_receives_and_subjects(String subject1, String subject2) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subject1);
        assertThat(responseContent).contains(subject2);
    }

    /******************************************************************************************* ADD SUBJECT TO COURSE*/
    @When("^the course client calls courses/subjects endpoint /courses/(\\d+)/subjects with \"([^\"]*)\"$")
    public void the_course_client_calls_courses_subjects_endpoint_courses_subjects_with(int courseId, String subjectName) throws Exception {
        Gson gson = new Gson();
        String subject = gson.toJson(new Subject.Builder().name(subjectName).build());
        result = mvc.perform(post("/courses/"+courseId+"/subjects").contentType(MediaType.APPLICATION_JSON).content(subject)).andDo(print()).andReturn();
    }

    @Then("^the course client receives \"([^\"]*)\" subject$")
    public void the_course_client_receives_subject(String subject) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subject);
    }

    /********************************************************************************************************** SHARED*/
    @Then("^the course client receives status code of (\\d+)$")
    public void the_course_client_receives_status_code_of(int status) throws Exception {
        assertThat(result.getResponse().getStatus()).isEqualTo(status);
    }

    @Then("^the course client receives pagination$")
    public void the_course_client_receives_pagination() throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("pageable");
        assertThat(responseContent).contains("totalPages");
        assertThat(responseContent).contains("totalElements");
        assertThat(responseContent).contains("numberOfElements");
    }
}
