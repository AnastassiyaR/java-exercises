package ee.taltech.iti0202.webbrowser;

import java.util.*;

public class WebBrowser {
    private String homePage = "google.com";
    private String currentUrl = homePage;
    private final Map<String, Integer> visitsPerUrl = new HashMap<>(); // {a=2, d=7}
    private final List<String> history = new ArrayList<>(); //
    private final Stack<String> historyBack = new Stack<>();
    private final Stack<String> historyForward = new Stack<>(); // [] list
    private final Set<String> bookmarks = new HashSet<>(); // [] like set in python

    // Конструктор
    public WebBrowser() {
        visitsPerUrl.put(homePage, 1); // Инициализация домашней страницы
        history.add(homePage); // Добавляем домашнюю страницу в историю
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (!historyBack.isEmpty()) {
            historyForward.push(currentUrl);
            currentUrl = historyBack.pop();
            visitsPerUrl.put(currentUrl, visitsPerUrl.getOrDefault(currentUrl, 0) + 1);
            history.add(currentUrl);
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (!historyForward.isEmpty()) {
            historyBack.push(currentUrl);
            currentUrl = historyForward.pop();
            visitsPerUrl.put(currentUrl, visitsPerUrl.getOrDefault(currentUrl, 0) + 1);
            history.add(currentUrl);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url where to go
     */
    public void goTo(String url) {
        if (!currentUrl.equals(url)) {
            historyBack.push(currentUrl);
            historyForward.clear();
            currentUrl = url;
            visitsPerUrl.put(url, visitsPerUrl.getOrDefault(url, 0) + 1);
            history.add(url);
        }
    }

    /**
     * Add the current webpage as a bookmark.
     */
    public void addAsBookmark() {
        bookmarks.add(currentUrl);
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    public List<String> getBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * Get top 3 visited pages.
     * If several pages are visited the same number of times,
     * first put the one that's been visited earlier for the first time
     * i.e. Homepage -> Facebook -> Twitter -> Facebook -> Homepage
     * output:
     * google.com - 2 visits
     * facebook.com - 2 visits
     * twitter.com - 1 visit
     *
     * hint: to fulfill the aforementioned requirement, you need a specific data structure for the page visit counter
     * Which one holds key-value pairs and preserves the order in which they were added?
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(visitsPerUrl.entrySet());

        sortedEntries.sort((a, b) -> {
            int compare = b.getValue().compareTo(a.getValue());
            if (compare == 0) {
                return history.indexOf(a.getKey()) - history.indexOf(b.getKey());
            }
            return compare;
        });

        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            if (count >= 3) {
                break;
            }
            String visitsText = entry.getValue() == 1 ? "visit" : "visits";
            result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" ")
                    .append(visitsText).append("\n");
            count++;
        }
        return result.toString().trim();
    }


    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return currentUrl;
    }

    public static void main(String[] args) {
        WebBrowser webBrowser = new WebBrowser();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        webBrowser.back();
        webBrowser.back();
        System.out.println(webBrowser.getHistory());  // [google.com, facebook.com, google.com]

        webBrowser = new WebBrowser();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.setHomePage("neti.ee");
        webBrowser.goTo("facebook.com");
        webBrowser.back();
        System.out.println(webBrowser.getCurrentUrl());  // google.com
        webBrowser.forward();
        System.out.println(webBrowser.getCurrentUrl());  // facebook.com

        System.out.println(webBrowser.getHistory()); // [google.com, facebook.com, google.com, facebook.com]
    }
}
