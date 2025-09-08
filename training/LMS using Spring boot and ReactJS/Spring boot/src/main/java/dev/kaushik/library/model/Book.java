package dev.kaushik.library.model;

import java.time.LocalDateTime;

import dev.kaushik.library.model.enums.BookAvailability;
import dev.kaushik.library.model.enums.BookCategory;
import dev.kaushik.library.model.enums.BookStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"createdAt","createdBy","updatedAt","updatedBy"})
public class Book {
	private Integer bookId;

	private String title;

	private String author;

	private BookCategory category;

	private BookStatus status;

	private BookAvailability availability;

	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;
}