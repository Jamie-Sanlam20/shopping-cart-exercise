import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

  // Store carts in memory (in real app, this would be a database)
  private Map<String, Map<String, Object>> carts = new HashMap<>();

  @Getter
  @Setter
  public class AddItemRequestDTO {
    private String cartId;
    private String itemName;
    private double price;
    private int quantity;
  }

  private double calculateTotal(Map<String, Object> cart) {
    double total = 0;
    for (String key : cart.keySet()) {
      String[] parts = key.split("_");
      double itemPrice = Double.parseDouble(parts[1]);
      int itemQty = (int) cart.get(key);
      total += itemPrice * itemQty;
    }
    return total;
  }

  @PostMapping("/addItem")
  public String addItem(@RequestBody AddItemRequestDTO request) {
    String cartId = request.getCartId();
    String itemName = request.getItemName();
    double price = request.getPrice();
    int quantity = request.getQuantity();

    // Get or create cart
    Map<String, Object> cart = carts.get(cartId);
    if (cart == null) {
      cart = new HashMap<>();
      carts.put(cartId, cart);
    }

    // Add item to cart
    String itemKey = itemName + "_" + price;
    if (cart.containsKey(itemKey)) {
      // Item already exists, update quantity
      int existingQty = (int) cart.get(itemKey);
      cart.put(itemKey, existingQty + quantity);
    } else {
      cart.put(itemKey, quantity);
    }

    // Calculate total
    double total = calculateTotal(cart);

    System.out.println("Cart " + cartId + " total: " + total);

    return "Item added. Total: " + total;
  }

  @GetMapping("/getTotal")
  public String getTotal(@RequestParam("cartId") String cartId) {
    Map<String, Object> cart = carts.get(cartId);

    if (cart == null) {
      return "Cart not found";
    }

    // Calculate total
    double total = calculateTotal(cart);

    return "Total: " + total;
  }
}
