package ProductControllerTest;

import com.gritgary.demo.Exceptions.ProductNotValidException;
import com.gritgary.demo.NoBsSpringBootApplication;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.ProductRepository;
import com.gritgary.demo.Product.commandhandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class CreateProductCommandHandlerTest {
    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    //Test Naming: MethodName_StateUnderTest_ExpectedBehavior
    @Test
    public void createProductCommandHandler_validProduct_returnSuccess(){
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Name");
        product.setDescription("Desc");
        product.setQuantity(10);

        ResponseEntity response = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException(){
        Product product = new Product();
        product.setId(1);
        product.setPrice(-9.99);
        product.setName("Name");
        product.setDescription("Desc");
        product.setQuantity(10);

        //return exception instead of status.
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        assertEquals("Price cannot be negative", exception.getSimpleResponse().getMessage());
    }
}
