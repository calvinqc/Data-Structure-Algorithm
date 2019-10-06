import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import java.util.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.*;
import java.net.URISyntaxException;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Main Program that generate the application
 */
public class Main extends Application {

    private static HashMap<String, BSTree> searchList = new HashMap<>();                // Each Search Keyword is map to its own BSTree
    private Scene HomePageScene, Display30ResultsScene, DisplayTop10SearchScene,        // Different Scenes of the UI
            insertURLScene, deleteURLScene, searchByPageRankScene;
    private TableView<Link> ThirtySearchURLsTable = new TableView<>();                  // The table to display 30 URLs
    private TableView<Node> TopTenSearchTable = new TableView<>();                      // The Table to display top 10 most searched keyword
    private String currentSearch;                                                       // The keyword that the user is currently search

    /**
     * This main will run the UI of the app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Build the GUI for the app
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        /** --------------------------- Buttons --------------------------- */
        Button searchButton  = new Button();                                            // Search Button
        searchButton.setText("Search");

        Button displayTop10UniqueSearchButton = new Button();                           // Home Button
        displayTop10UniqueSearchButton.setText("Display Top 10 Unique Searches");

        Button homeButton = new Button();                                               // Home Button
        homeButton.setText("HomePage");

        Button sortByIndex = new Button();                                              // Sort By Index Button
        sortByIndex.setText("Sort By Index");

        Button openSearchPageRankButton = new Button();                                 // Open Search PageRank Button
        openSearchPageRankButton.setText("Open Search PageRank Page");

        Button searchPageRankButton = new Button();                                     // Search PageRank Button
        searchPageRankButton.setText("Search PageRank");

        Button insertURLButton = new Button();                                          // Insert new URL Button
        insertURLButton.setText("Insert URL");

        Button openInsertURLButton = new Button();                                      // Open Insert URL Page Button
        openInsertURLButton.setText("Open Insert URL Page");

        Button deleteURLButton = new Button();                                          // Delete URL Button
        deleteURLButton.setText("Delete URL");

        Button openDeleteURLButton = new Button();                                      // Open Delete URL Page Button
        openDeleteURLButton.setText("Open Delete URL Page");

        Button sortBSTButton = new Button();                                            // sort the BSTree By PageRank Button
        sortBSTButton.setText("Sort BST");

        Button quickSortButton = new Button();                                          // Sort by Quicksort Button
        quickSortButton.setText("Quick Sort");

        Button sortByDomainNameButton = new Button();                                   // Sort by Domain Name Button
        sortByDomainNameButton.setText("Sort By Name");

        /** --------------------------- Table Config --------------------------- */
        ThirtySearchURLsTable.setEditable(true);
        TableColumn PageRank = new TableColumn("Page Rank");
        PageRank.setCellValueFactory(
                new PropertyValueFactory<Link, String>("pageRank"));

        TableColumn DomainName = new TableColumn("Domain Name");
        DomainName.setCellValueFactory(
                new PropertyValueFactory<Link, String>("URL"));

        TableColumn TotalScore = new TableColumn("Total Score");
        TotalScore.setCellValueFactory(
                new PropertyValueFactory<Link, String>("totalScore"));

        TableColumn Index = new TableColumn("Index");
        Index.setCellValueFactory(
                new PropertyValueFactory<Link, String>("index"));

        TopTenSearchTable.setEditable(true);
        TableColumn Keyword = new TableColumn("Keyword");
        Keyword.setCellValueFactory(
                new PropertyValueFactory<Node, String>("searchName"));

        TableColumn NumSearch = new TableColumn("Number of Search");
        NumSearch.setCellValueFactory(
                new PropertyValueFactory<Node, String>("totalSearch"));



        /** --------------------------- HomePage --------------------------- */
        VBox HomePageVBOX = new VBox(10);
        Label homepageTitle = new Label("Welcome to SERP Simulator!");
        homepageTitle.setTextFill(Color.DARKBLUE);
        homepageTitle.setTextAlignment(TextAlignment.CENTER);
        homepageTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        TextField searchText = new TextField();
        searchText.setPromptText("Enter your keyword:");

        HomePageScene  = new Scene(HomePageVBOX, 5000, 5000);

        /** --------------------------- Display 30 URLS --------------------------- */
        VBox ThirtyResultsPage = new VBox(10);
        Label ThirtySearchURLLabel = new Label("Display 30 Search Results!");
        ThirtySearchURLLabel.setTextAlignment(TextAlignment.CENTER);
        ThirtySearchURLLabel.setTextFill(Color.DARKBLUE);
        ThirtySearchURLLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        Display30ResultsScene = new Scene(ThirtyResultsPage, 5000, 5000);

        /** --------------------------- Display Top 10 most search keywords --------------------------- */
        VBox topTenUniqueSearchPage = new VBox(10);
        Label topTenUniqueSearchLabel = new Label("Display top 10 Unique Search");
        topTenUniqueSearchLabel.setTextAlignment(TextAlignment.CENTER);
        topTenUniqueSearchLabel.setTextFill(Color.DARKBLUE);
        topTenUniqueSearchLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        DisplayTop10SearchScene = new Scene(topTenUniqueSearchPage, 5000, 5000);

        /** --------------------------- Insert new URL to a BST --------------------------- */
        VBox insertURLVBOX = new VBox(10);
        Label insertURLLabel = new Label("Insert a new URL");
        insertURLLabel.setTextAlignment(TextAlignment.CENTER);
        insertURLLabel.setTextFill(Color.DARKBLUE);
        insertURLLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        TextField urlNameToInsert   = new TextField();
        urlNameToInsert.setPromptText("Enter your FULL link:");
        TextField factor1   = new TextField();
        factor1.setPromptText("Enter your Factor 1:");
        TextField factor2   = new TextField();
        factor2.setPromptText("Enter your Factor 2:");
        TextField factor3   = new TextField();
        factor3.setPromptText("Enter your Factor 3:");
        TextField factor4   = new TextField();
        factor4.setPromptText("Enter your Factor 4:");

        insertURLScene = new Scene(insertURLVBOX, 5000, 5000);

        /** --------------------------- Delete a URL in a BST --------------------------- */
        VBox deleteURLVBOX = new VBox(10);
        Label deleteURLLabel = new Label("Choose a PageRank to Delete");
        deleteURLLabel.setTextAlignment(TextAlignment.CENTER);
        insertURLLabel.setTextFill(Color.DARKBLUE);
        insertURLLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        TextField urlAtPageRankToDelete   = new TextField();
        urlAtPageRankToDelete.setPromptText("Enter the PageRank you want to delete:");

        deleteURLScene = new Scene(deleteURLVBOX, 5000, 5000);

        /** --------------------------- Search By PageRank in a BST --------------------------- */
        VBox searchPageRankVBOX = new VBox(10);
        Label searchPageRankLabel = new Label("Search a URL by PageRank");
        searchPageRankLabel.setTextAlignment(TextAlignment.CENTER);
        searchPageRankLabel.setTextFill(Color.DARKBLUE);
        searchPageRankLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

        TextField pageRankToSearch   = new TextField();
        pageRankToSearch.setPromptText("Enter the PageRank you want to search:");

        searchByPageRankScene = new Scene(searchPageRankVBOX, 5000, 5000);

        /** --------------------------- HBox Config --------------------------- */
        HBox searchHbox = new HBox(5,
                searchText,
                searchButton
        );

        HBox mainOption = new HBox(5,
                homeButton,
                displayTop10UniqueSearchButton,
                openInsertURLButton,
                openDeleteURLButton,
                openSearchPageRankButton,
                sortByIndex,
                sortBSTButton,
                sortByDomainNameButton,
                quickSortButton
        );

        HBox deleteHbox = new HBox(5,
                urlAtPageRankToDelete,
                deleteURLButton
        );

        HBox insertHbox = new HBox(5,
                urlNameToInsert,
                factor1,
                factor2,
                factor3,
                factor4,
                insertURLButton
        );

        HBox searchPageRankHbox = new HBox(5,
                pageRankToSearch,
                searchPageRankButton
        );

        /** --------------------------- All Buttons' action --------------------------- */
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Link> resultView = searchList.get(currentSearch).getArrayListByIndex();
                initTable(ThirtySearchURLsTable, resultView);
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(Display30ResultsScene);
            }
        });

        /**
         * Search Button: Display 30 URLs Results in Ascending Index
         */
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentSearch = searchText.getText();
                ArrayList<Link> resultView;

                ThirtySearchURLLabel.setText("Display 30 Search Results of " + currentSearch);
                if (!searchList.containsKey(currentSearch)) {
                    WebCrawler search = new WebCrawler();
                    BSTree tree = search.createBST(currentSearch);
                    searchList.put(currentSearch, tree);
                    resultView = tree.getArrayListByIndex();
                } else {
                    searchList.get(currentSearch).increaseTotalSearch();
                    resultView = searchList.get(currentSearch).getArrayListByIndex();
                }

                initTable(ThirtySearchURLsTable, resultView);
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );

                primaryStage.setScene(Display30ResultsScene);
            }
        });

        /**
         * SortBST Button: Sort 30 URLs in ascending PAGERANK using BST sort
         */
        sortBSTButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).ArrayListOfTreeByScore());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );

                primaryStage.setScene(Display30ResultsScene);
            }
        });

        /**
         * Display Top 10 Unique Button: Display most searched keywrod by the user
         */
        displayTop10UniqueSearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Node> resultList = new ArrayList<>();
                for (BSTree temp : searchList.values()) {
                    Node newNode = new Node(temp.getSearchKeyword(), temp.getTotalSearch());
                    resultList.add(newNode);
                }
                Collections.sort(resultList);

                initTable(TopTenSearchTable, resultList);
                TopTenSearchTable.getColumns().addAll(Keyword, NumSearch);

                topTenUniqueSearchPage.getChildren().clear();
                topTenUniqueSearchPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        TopTenSearchTable
                );

                primaryStage.setScene(DisplayTop10SearchScene);
            }
        });

        /**
         * Open the Delete URL By PageRank
         */
        openDeleteURLButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).ArrayListOfTreeByScore());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                deleteURLVBOX.getChildren().clear();
                deleteURLVBOX.getChildren().addAll(
                        mainOption,
                        deleteURLLabel,
                        deleteHbox,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(deleteURLScene);
            }
        });

        /**
         * Search the URL based on the PageRank and delete the found URL
         */
        deleteURLButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String nameAtPageRankToDeleteText = urlAtPageRankToDelete.getText();
                Link urlToDelete = searchList.get(currentSearch).searchByPageRank(Integer.parseInt(nameAtPageRankToDeleteText));
                searchList.get(currentSearch).remove(urlToDelete);

                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).getArrayListByIndex());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(Display30ResultsScene);
            }
        });


        /**
         * Open the Insert a new URL Form
         */
        openInsertURLButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).ArrayListOfTreeByScore());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                insertURLVBOX.getChildren().clear();
                insertURLVBOX.getChildren().addAll(
                        mainOption,
                        insertURLLabel,
                        insertHbox,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(insertURLScene);
            }
        });

        /**
         * Insert a new URL into BST Tree and display new BST in Ascending Index Order
         */
        insertURLButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String urlToInsertText = urlNameToInsert.getText();
                int factor1Num = Integer.parseInt(factor1.getText());
                int factor2Num = Integer.parseInt(factor2.getText());
                int factor3Num = Integer.parseInt(factor3.getText());
                int factor4Num = Integer.parseInt(factor4.getText());
                int size = searchList.get(currentSearch).getOriginalIndex();

                Link urlToInsert = new Link("", urlToInsertText, size + 1, factor1Num, factor2Num, factor3Num, factor4Num);
                searchList.get(currentSearch).insertByScore(urlToInsert);

                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).getArrayListByIndex());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );

                primaryStage.setScene(Display30ResultsScene);
            }
        });


        /**
         * Open Search URL By PageRank Form
         */
        openSearchPageRankButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 initTable(ThirtySearchURLsTable, searchList.get(currentSearch).ArrayListOfTreeByScore());
                 ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                 searchPageRankVBOX.getChildren().clear();
                 searchPageRankVBOX.getChildren().addAll(
                         mainOption,
                         searchPageRankLabel,
                         searchPageRankHbox,
                         ThirtySearchURLsTable
                 );
                 primaryStage.setScene(searchByPageRankScene);
             }
         });


        /**
         * Search the URL By PageRank and display in Ascending Index Order
         */
        searchPageRankButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String pageRankText = pageRankToSearch.getText();
                Link urlToDisplay = searchList.get(currentSearch).searchByPageRank(Integer.parseInt(pageRankText));
                ArrayList<Link> result = new ArrayList<>();
                result.add(urlToDisplay);

                initTable(ThirtySearchURLsTable, result);
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(Display30ResultsScene);
            }
        });

        /**
         * Sort 30 URLs using QuickSort
         */
        quickSortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SortingAlgorithm sort = new SortingAlgorithm();
                ArrayList<Link> newArray = new ArrayList<>();
                newArray.addAll(searchList.get(currentSearch).getArrayListByIndex());
                sort.quickSort(newArray, 0, newArray.size() - 1);

                initTable(ThirtySearchURLsTable, newArray);
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(Display30ResultsScene);
            }
        });

        /**
         * Sort 30 URLs of the current Search by Domain Name using Bucket Sort
         */
        sortByDomainNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SortingAlgorithm sort = new SortingAlgorithm();
                    ArrayList<Link> newArray;
                    newArray = sort.bucketSortByDomainName(searchList.get(currentSearch).getArrayListByIndex());
                    initTable(ThirtySearchURLsTable, newArray);
                    ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                    ThirtyResultsPage.getChildren().clear();
                    ThirtyResultsPage.getChildren().addAll(
                            mainOption,
                            searchHbox,
                            ThirtySearchURLLabel,
                            ThirtySearchURLsTable
                    );
                    primaryStage.setScene(Display30ResultsScene);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * Sort 30 URLs by Ascending Index
         */
        sortByIndex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                initTable(ThirtySearchURLsTable, searchList.get(currentSearch).getArrayListByIndex());
                ThirtySearchURLsTable.getColumns().addAll(Index, DomainName, TotalScore, PageRank);

                ThirtyResultsPage.getChildren().clear();
                ThirtyResultsPage.getChildren().addAll(
                        mainOption,
                        searchHbox,
                        ThirtySearchURLLabel,
                        ThirtySearchURLsTable
                );
                primaryStage.setScene(Display30ResultsScene);
            }
        });

        HomePageVBOX.getChildren().addAll(
                mainOption,
                homepageTitle,
                searchHbox
        );
        primaryStage.setTitle("Google Search Engine Results Page");
        primaryStage.setScene(HomePageScene);
        primaryStage.show();
    }

    /**
     * Initialize the Table with a set of Data
     * @param table: Table Name
     * @param input: the data to be inserted in the table
     */
    private void initTable(TableView table, ArrayList input) {
        ObservableList resultView;
        resultView = FXCollections.observableArrayList(input);
        table.getColumns().clear();
        table.setItems(resultView);
    }
}