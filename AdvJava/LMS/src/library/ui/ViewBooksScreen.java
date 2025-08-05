package library.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

public class ViewBooksScreen {

	@FXML
	private TableView<Book> booksTableView;
	@FXML
	private TableColumn<Book, Void> selectColumn;

	@FXML
	private TableColumn<Book, Integer> bookIdColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, String> categoryColumn;
	@FXML
	private TableColumn<Book, String> statusColumn;
	@FXML
	private TableColumn<Book, String> availabilityColumn;

	@FXML
	private TableColumn<Book, Void> actionsColumn;
	@FXML
	private TableColumn<Book, Void> singleAvailabilityActionColumn;

	@FXML
	private TableColumn<Book, LocalDateTime> createdAtColumn;
	@FXML
	private TableColumn<Book, String> createdByColumn;
	@FXML
	private TableColumn<Book, LocalDateTime> updatedAtColumn;
	@FXML
	private TableColumn<Book, String> updatedByColumn;

	@FXML
	private Label messageLabel;

	private BookService bookService;
	private final String CURRENT_USER = "ADMIN";

	private Map<Integer, Boolean> selectedBookIds = new HashMap<>();

	public ViewBooksScreen() {
		this.bookService = new BookServiceImpl();
	}

	@FXML
	private void initialize() {
		selectColumn.setCellFactory(param -> new TableCell<Book, Void>() {
			private final CheckBox checkBox = new CheckBox();
			{
				checkBox.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					if (book != null) {
						selectedBookIds.put(book.getBookId(), checkBox.isSelected());
                        getTableView().refresh();
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					Book book = getTableView().getItems().get(getIndex());
					if (book != null) {
						checkBox.setSelected(selectedBookIds.getOrDefault(book.getBookId(), false));
					}
					setGraphic(checkBox);
				}
			}
		});
		booksTableView.setEditable(true);

		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

		categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
				cellData.getValue().getCategory().getDisplayName()));
		statusColumn.setCellValueFactory(
				cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus().toString()));
		availabilityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
				cellData.getValue().getAvailability().toString()));

		DateTimeFormatter auditFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		if (createdAtColumn != null) {
			createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
			createdAtColumn.setCellFactory(column -> new TableCell<Book, LocalDateTime>() {
				@Override
				protected void updateItem(LocalDateTime item, boolean empty) {
					super.updateItem(item, empty);
					setText(empty || item == null ? null : item.format(auditFormatter));
				}
			});
		}
		if (createdByColumn != null) {
			createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
		}
		if (updatedAtColumn != null) {
			updatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
			updatedAtColumn.setCellFactory(column -> new TableCell<Book, LocalDateTime>() {
				@Override
				protected void updateItem(LocalDateTime item, boolean empty) {
					super.updateItem(item, empty);
					setText(empty || item == null ? null : item.format(auditFormatter));
				}
			});
		}
		if (updatedByColumn != null) {
			updatedByColumn.setCellValueFactory(new PropertyValueFactory<>("updatedBy"));
		}

		actionsColumn.setCellFactory(param -> new TableCell<Book, Void>() {
			private final Button updateButton = new Button("Update");
			private final Button deleteButton = new Button("Delete");
			private final HBox pane = new HBox(5, updateButton, deleteButton);

			{

				updateButton.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					handleUpdateBookRow(book, event);
				});
				deleteButton.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					handleDeleteBookRow(book, event);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				
				super.updateItem(item, empty);
				
				if(empty) {
					setGraphic(null);					
				}else {
                    Book book = getTableView().getItems().get(getIndex());
                    if (book != null) {
                        boolean isSelected = selectedBookIds.getOrDefault(book.getBookId(), false);
                        updateButton.setDisable(isSelected);
                        deleteButton.setDisable(isSelected);
                    }
                    setGraphic(pane);
				}
			}
		});

		singleAvailabilityActionColumn.setCellFactory(param -> new TableCell<Book, Void>() {
			private final Button toggleAvailabilityButton = new Button();
			private final HBox pane = new HBox(5, toggleAvailabilityButton);

			{
				toggleAvailabilityButton.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					BookAvailability newAvailability = (book.getAvailability() == BookAvailability.AVAILABLE)
							? BookAvailability.ISSUED
							: BookAvailability.AVAILABLE;
					updateBookAvailabilityRow(book, newAvailability, event);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					Book book = getTableView().getItems().get(getIndex());
					if (book != null) {
						toggleAvailabilityButton.setText(book.getAvailability() == BookAvailability.AVAILABLE ? "Mark Issued": "Mark Available");
						toggleAvailabilityButton.setDisable(book.getStatus() == BookStatus.INACTIVE);

                        boolean isSelected = selectedBookIds.getOrDefault(book.getBookId(), false);
                        toggleAvailabilityButton.setDisable(isSelected);
					} else {
						toggleAvailabilityButton.setText("N/A");
						toggleAvailabilityButton.setDisable(true);
					}
					setGraphic(pane);
				}
			}
		});

		Label noContentLabel = new Label("No books found in the library.");
		noContentLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 16px; -fx-alignment: center;");
		booksTableView.setPlaceholder(noContentLabel);

		loadBooks();
	}

	@FXML
	private void handleRefreshBooks() {
		loadBooks();
	}

	private void loadBooks() {
		try {
			List<Book> books = bookService.findBooks(Collections.emptyMap());
			ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
			booksTableView.setItems(observableBooks);

			messageLabel.setText("");

			selectedBookIds.clear();

		} catch (LibraryException e) {
			messageLabel.setText("Database error: " + e.getMessage());
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
//			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred: " + e.getMessage());
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
//			e.printStackTrace();
		}
	}

	private void handleUpdateBookRow(Book book, ActionEvent event) {
		System.out.println(event);
		if (book != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("UpdateBookForm.fxml"));
				Parent root = loader.load();

				UpdateBookForm controller = loader.getController();
				controller.setBook(book);

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root,900,600);
				stage.setScene(scene);
				stage.setTitle("Library Management System - Update Book");
				stage.show();
			} catch (IOException e) {
				messageLabel.setText("Error loading update form: " + e.getMessage());
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
//				e.printStackTrace();
			} catch (Exception e) {
				messageLabel.setText("An unexpected error occurred during update form load: " + e.getMessage());
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
//				e.printStackTrace();
			}
		} else {
			messageLabel.setText("Error: No book selected for update operation (internal error).");
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
		}
	}

	private void handleDeleteBookRow(Book book, ActionEvent event) {
		if (book != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirm Deletion");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to delete the book: " + book.getTitle() + " (ID: "
					+ book.getBookId() + ")?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				try {
					boolean deleted = bookService.deleteBook(book.getBookId());
					if (deleted) {
						messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
						messageLabel.setText("Book ID " + book.getBookId() + " deleted successfully.");
						loadBooks();
					} else {
						messageLabel.setTextFill(javafx.scene.paint.Color.RED);
						messageLabel.setText("Failed to delete book ID " + book.getBookId() + ". Book not found.");
					}
				} catch (LibraryException e) {
					messageLabel.setTextFill(javafx.scene.paint.Color.RED);
					messageLabel.setText(e.getMessage());
				} catch (Exception e) {
					messageLabel.setTextFill(javafx.scene.paint.Color.RED);
					messageLabel.setText("An unexpected error occurred: " + e.getMessage());
//					e.printStackTrace();
				}
			}
		} else {
			messageLabel.setText("Error: No book selected for delete operation (internal error).");
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
		}
	}

	private void updateBookAvailabilityRow(Book book, BookAvailability newAvailability, ActionEvent event) {
		System.out.println(event);
		if (book != null) {
			String statusText = newAvailability.toString().toLowerCase();
			try {
				boolean updated = bookService.updateBookAvailability(book.getBookId(), newAvailability.getCode(),
						CURRENT_USER);
				if (updated) {
					messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
					messageLabel.setText("Book ID " + book.getBookId() + " marked as " + statusText + ".");
					loadBooks();
				} else {
					messageLabel.setTextFill(javafx.scene.paint.Color.RED);
					messageLabel.setText(
							"Failed to mark book ID " + book.getBookId() + " as " + statusText + ". Book not found.");
				}
			} catch (LibraryException e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText(e.getMessage());
			} catch (Exception e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText("An unexpected error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Error: No book selected for availability update (internal error).");
		}
	}

	@FXML
	private void handleDeleteSelectedBatch() {
		List<Integer> bookIdsToDelete = selectedBookIds.entrySet().stream().filter(Map.Entry::getValue)
				.map(Map.Entry::getKey).collect(Collectors.toList());

		if (bookIdsToDelete.isEmpty()) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Please select books to delete.");
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Batch Deletion");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete " + bookIdsToDelete+ " selected books?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				boolean results = bookService.deleteBooksBatch(bookIdsToDelete);
//				int successCount = 0;
//				for (boolean s : results) {
//					if (s)
//						successCount++;
//				}
				if(results) {
					messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
					messageLabel.setText(bookIdsToDelete + " books deleted successfully.");
				}
				loadBooks();
			} catch (LibraryException e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText(e.getMessage());
			} catch (Exception e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText("An unexpected error occurred during batch delete: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void handleUpdateAvailSelectedBatch() {
		List<Integer> bookIdsToUpdate = selectedBookIds.entrySet().stream().filter(Map.Entry::getValue)
				.map(Map.Entry::getKey).collect(Collectors.toList());

		if (bookIdsToUpdate.isEmpty()) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Please select books to Update availability.");
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Batch Update availability");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to update availability for " + bookIdsToUpdate+ " selected books?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				boolean results = bookService.updateBookAvailabilityBatch(bookIdsToUpdate,CURRENT_USER);
//				int successCount = 0;
//				for (boolean s : results) {
//					if (s)
//						successCount++;
//				}
				if(results) {
					messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
					messageLabel.setText(bookIdsToUpdate + " books updated successfully.");
				}
				loadBooks();
			} catch (LibraryException e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText(e.getMessage());
			} catch (Exception e) {
				messageLabel.setTextFill(javafx.scene.paint.Color.RED);
				messageLabel.setText("An unexpected error occurred during batch update availability: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	
	@FXML
	private void handleBackToMainMenu(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Library Management System - Main Menu");
			stage.show();
		} catch (IOException e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Error navigating back to main menu: " + e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("An unexpected error occurred during navigation: " + e.getMessage());
//			e.printStackTrace();
		}
	}
}