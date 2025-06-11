package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {

    @Test
    void testHomePage() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.homePage();
        assertEquals("google.com", browser.getCurrentUrl());
    }

    @Test
    void testGoTo() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        assertEquals("facebook.com", browser.getCurrentUrl());
        assertTrue(browser.getHistory().contains("facebook.com"));
    }

    @Test
    void testBack() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.back();
        assertEquals("facebook.com", browser.getCurrentUrl());
    }

    @Test
    void testForward() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.back();
        browser.forward();
        assertEquals("twitter.com", browser.getCurrentUrl());
    }

    @Test
    void testAddAsBookmark() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.addAsBookmark();
        assertTrue(browser.getBookmarks().contains("facebook.com"));
    }

    @Test
    void testRemoveBookmark() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.addAsBookmark();
        browser.removeBookmark("facebook.com");
        assertFalse(browser.getBookmarks().contains("facebook.com"));
    }

    @Test
    void testSetHomePage() {
        WebBrowser browser = new WebBrowser();
        browser.setHomePage("neti.ee");
        browser.homePage();
        assertEquals("neti.ee", browser.getCurrentUrl());
    }

    @Test
    void testGetTop3VisitedPages() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.goTo("facebook.com");
        browser.goTo("instagram.com");

        String expected = "facebook.com - 2 visits\n" +
                "google.com - 1 visit\n" +
                "twitter.com - 1 visit";
        assertEquals(expected, browser.getTop3VisitedPages());
    }

    @Test
    void testGetHistory() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.back();

        List<String> expectedHistory = List.of("google.com", "facebook.com", "twitter.com", "facebook.com");
        assertEquals(expectedHistory, browser.getHistory());
    }

    @Test
    void testGetCurrentUrl() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        assertEquals("facebook.com", browser.getCurrentUrl());
    }

    @Test
    void testNewNavigationClearsForwardHistory() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.back();
        browser.goTo("instagram.com");
        browser.forward();
        assertEquals("instagram.com", browser.getCurrentUrl());
    }

    @Test
    void testForwardStopsWhenNoMoreHistory() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.goTo("twitter.com");
        browser.back();
        browser.forward();
        browser.forward();
        assertEquals("twitter.com", browser.getCurrentUrl());
    }

    @Test
    void testBackStopsWhenNoMoreHistory() {
        WebBrowser browser = new WebBrowser();
        browser.goTo("facebook.com");
        browser.back();
        browser.back();
        assertEquals("google.com", browser.getCurrentUrl());
    }
}
