package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideWikiSoftAsertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy="eager";
    }

    @Test
    void selenidePageShouldHaveSoftAssertionsPageAndJUnit5CodeInIt() {

        // Open Selenide in github
        open("https://github.com/selenide/selenide");
        // Open Wiki module
        $("#wiki-tab").click();
        // Verify that there is 'SoftAssertions' page in the list of pages
        $(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        // Open page SoftAssertion
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        //Verify that on the page there is code sample for JUnit5
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:\n" +
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
