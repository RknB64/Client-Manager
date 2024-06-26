package cda.clientmanager.controller.utils;

public class SearchManager {

    private static SearchManager instance = null;
    private static String searchBy;

    private SearchManager() {

    }

    public static SearchManager getInstance() {
        if (instance == null) {
            instance = new SearchManager();
        }
        return instance;
    }

    public static String getSearchBy() {
        return searchBy;
    }

    public static void setSearchBy(String searchBy) {
        SearchManager.searchBy = searchBy;
    }
}
