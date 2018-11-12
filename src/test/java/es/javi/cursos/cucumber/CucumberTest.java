package es.javi.cursos.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import es.javi.cursos.Application;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"es.javi.cursos.controller.it"})
@ContextConfiguration(classes = Application.class)
public class CucumberTest {}
