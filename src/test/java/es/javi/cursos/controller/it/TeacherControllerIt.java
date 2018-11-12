package es.javi.cursos.controller.it;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
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
public class TeacherControllerIt {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MvcResult result;

    @Before
    public void beforeScenario() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /************************************************************************************************ GET ALL TEACHERS*/
    @When("^the teacher client calls teachers endpoint /teachers by page of (\\d+) and size of (\\d+)$")
    public void the_teacher_client_calls_teachers_endpoint_teachers_by_page_of_and_size_of(int page, int size) throws Exception {
        result = mvc.perform(get("/teachers").param("page", ""+page).param("size", ""+size)).andDo(print()).andReturn();
    }

    @Then("^the teacher client receives \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" teachers")
    public void the_teacher_client_receives_and_teachers(String course1, String course2, String course3, String course4, String course5) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(course1);
        assertThat(responseContent).contains(course2);
        assertThat(responseContent).contains(course3);
        assertThat(responseContent).contains(course4);
        assertThat(responseContent).contains(course5);
    }

    /***************************************************************************************************** ADD TEACHER*/
    @When("^the teacher client calls teachers endpoint /teachers with \"([^\"]*)\"$")
    public void the_teacher_client_calls_teachers_endpoint_teachers_with(String teacherName) throws Exception {
        Gson gson = new Gson();
        String teacher = gson.toJson(new Teacher.Builder().name(teacherName).build());
        result = mvc.perform(post("/teachers").contentType(MediaType.APPLICATION_JSON).content(teacher)).andDo(print()).andReturn();
    }

    @Then("^the teacher client receives \"([^\"]*)\" teacher$")
    public void the_teacher_client_receives_teacher(String teacher) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(teacher);
    }

    /******************************************************************************************* ADD COURSE TO TEACHER*/
    @When("^the teacher client calls teachers/courses endpoint /teachers/(\\d+)/courses with \"([^\"]*)\"$")
    public void the_teacher_client_calls_teachers_courses_endpoint_teachers_courses_with(int teacherId, String courseName) throws Exception {
        Gson gson = new Gson();
        String course = gson.toJson(new Course.Builder().name(courseName).active(true).level("basico").build());
        result = mvc.perform(post("/teachers/"+teacherId+"/courses").contentType(MediaType.APPLICATION_JSON).content(course)).andDo(print()).andReturn();
    }

    @Then("^the teacher client receives \"([^\"]*)\" subject$")
    public void the_teacher_client_receives_subject(String subject) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains(subject);
    }

    /********************************************************************************************************** SHARED*/
    @Then("^the teacher client receives status code of (\\d+)$")
    public void the_teacher_client_receives_status_code_of(int status) throws Exception {
        assertThat(result.getResponse().getStatus()).isEqualTo(status);
    }

    @Then("^the teacher client receives pagination$")
    public void the_teacher_client_receives_pagination() throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("pageable");
        assertThat(responseContent).contains("totalPages");
        assertThat(responseContent).contains("totalElements");
        assertThat(responseContent).contains("numberOfElements");
    }
}
