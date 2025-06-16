
package controller;


import java.util.List;

import Pojo.Book;
import Service.LibraryService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBooks {
    public VBox getView() throws Exception {
        VBox root = new VBox(10);
        Stage viewStage = new Stage();
        TableView<Book> table = new TableView<>();
        TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Book, Character> availCol = new TableColumn<>("Availability");
        availCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        table.getColumns().addAll(List.of(idCol, titleCol, authorCol, catCol, statusCol, availCol));

        LibraryService lib = new LibraryService();
        List<Book> books = lib.viewAllBooks();
        table.getItems().addAll(books);

        root.getChildren().addAll(new Label("All Books"), table);
        root.setAlignment(Pos.CENTER);
        viewStage.setScene(new Scene(root, 700, 400));
        viewStage.show();
        return root;
    }
}
