package com.develop.internetshop;

import com.develop.internetshop.controllers.api.BaseApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class BaseApiControllerTests {

	@Mock
	private CrudRepository<Object, Object> repository;

	private BaseApiController<Object, Object> controller;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		controller = new BaseApiController<>(repository);
	}

	@Test
	void testGetProduct() {
		// Arrange
		Object expectedProduct = new Object();
		when(repository.findById(any())).thenReturn(Optional.of(expectedProduct));
		String id = "123";

		// Act
		Object result = controller.getProduct(id).get();

		// Assert
		verify(repository, times(1)).findById(id);
		assertEquals(expectedProduct, result);
	}

	@Test
	void testGetProductWithMadId() {
		// Arrange
		Object expectedProduct = new Object();
		when(repository.findById(any())).thenReturn(Optional.of(expectedProduct));
		String id = "wdwd_";

		// Act
		Object result = controller.getProduct(id).get();

		// Assert
		verify(repository, times(1)).findById(id);
		assertEquals(expectedProduct, result);
	}

	@Test
	void testGetProductMadId() {
		// Arrange
		Object expectedProduct = new Object();
		when(repository.findById(any())).thenReturn(Optional.of(expectedProduct));
		String id = "";

		// Act
		Object result = controller.getProduct(id).get();

		// Assert
		verify(repository, times(1)).findById(id);
		assertEquals(expectedProduct, result);
	}

	@Test
	void testPostProduct() {
		// Arrange
		Object product = new Object();
		when(repository.save(product)).thenReturn(product);

		// Act
		Object result = controller.postProduct(product);

		// Assert
		verify(repository, times(1)).save(product);
		assertEquals(product, result);
	}

	@Test
	void testDeleteProduct_existingProduct() {
		// Arrange
		when(repository.existsById(any())).thenReturn(true);
		String id = "123";

		// Act
		controller.deleteProduct(id);

		// Assert
		verify(repository, times(1)).existsById(id);
		verify(repository, times(1)).deleteById(id);
	}

	@Test
	void testDeleteProduct_nonExistentProduct() {
		// Arrange
		when(repository.existsById(any())).thenReturn(false);
		String id = "123";

		// Act
		controller.deleteProduct(id);

		// Assert
		verify(repository, times(1)).existsById(id);
		verify(repository, never()).deleteById(id);
	}
}

