package ProductControllerTest;

import com.gritgary.demo.Exceptions.ProductNotFoundException;
import com.gritgary.demo.Exceptions.ProductNotValidException;
import com.gritgary.demo.NoBsSpringBootApplication;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.Model.ProductDTO;
import com.gritgary.demo.Product.ProductRepository;
import com.gritgary.demo.Product.queryhandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class GetProductQueryHandlerTest {
    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validId_returnsProductDTO(){
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Name");
        product.setDescription("Desc");
        product.setQuantity(10);

        ProductDTO expectedDTO = new ProductDTO(product);

        //mock response
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());

        assertEquals(expectedDTO, actualResponse.getBody());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getProductQueryHandler_inValidId_throwsProductNotFoundException(){
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
        assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());
    }
}
