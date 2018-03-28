package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import business.service.CashierService;
import dataAccess.dbmodel.CashierDto;
import dataAccess.repository.CashierRepositoryMySql;

class EncryptTest {

	public static CashierRepositoryMySql setup() {

		CashierRepositoryMySql mockedCashierRepository = Mockito.mock(CashierRepositoryMySql.class);

		CashierDto cashier1 = new CashierDto();
		cashier1.setIdUser(14);
		cashier1.setUsername("adela");
		cashier1.setPassword("a562cfa07c2b1213b3a5c99b756fc206");
		cashier1.setRole("cashier");

		CashierDto cashier2 = new CashierDto();
		cashier2.setIdUser(15);
		cashier2.setUsername("amy");
		cashier2.setPassword("4a2028eceac5e1f4d252ea13c71ecec6");
		cashier2.setRole("cashier");

		CashierDto admin = new CashierDto();
		admin.setIdUser(20);
		admin.setUsername("admin");
		admin.setPassword("21232f297a57a5a743894a0e4a801fc3");
		admin.setRole("admin");

		Mockito.when(mockedCashierRepository.getAll()).thenReturn(Arrays.asList(cashier1, cashier2, admin));
		return mockedCashierRepository;

	}

	@Test
	void Login_ExistingCashier_Cashier() {

		// Arrange
		String username = "adela";
		String password = "ade";
		CashierService cashierService = new CashierService();
		CashierRepositoryMySql mockedCashierRepository = EncryptTest.setup();
		cashierService.setCashierRepository(mockedCashierRepository);

		// Act
		String expected = cashierService.login(username, password);

		// Assert
		assertEquals(expected, "cashier");
	}

	@Test
	void Login_ExistingAdmin_Admin() {

		// Arrange
		String username = "admin";
		String password = "admin";
		CashierService cashierService = new CashierService();
		CashierRepositoryMySql mockedCashierRepository = EncryptTest.setup();
		cashierService.setCashierRepository(mockedCashierRepository);

		// Act
		String expected = cashierService.login(username, password);

		// Assert
		assertEquals(expected, "admin");
	}

	@Test
	void Login_ExistingCashier_ThePasswordIsNotValid() {

		// Arrange
		String username = "adela";
		String password = "aaaa";
		CashierService cashierService = new CashierService();
		CashierRepositoryMySql mockedCashierRepository = EncryptTest.setup();
		cashierService.setCashierRepository(mockedCashierRepository);

		// Act
		String expected = cashierService.login(username, password);

		// Assert
		assertEquals(expected, "The password is not valid");
	}
	
	@Test
	void Login_NotExistingCashier_TheUsernameIsNotValid() {

		// Arrange
		String username = "paul";
		String password = "ade";
		CashierService cashierService = new CashierService();
		CashierRepositoryMySql mockedCashierRepository = EncryptTest.setup();
		cashierService.setCashierRepository(mockedCashierRepository);

		// Act
		String expected = cashierService.login(username, password);

		// Assert
		assertEquals(expected, "The username is not valid");
	}

}
