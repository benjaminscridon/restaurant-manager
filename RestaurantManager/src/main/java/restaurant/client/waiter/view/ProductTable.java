package restaurant.client.waiter.view;
import javafx.scene.image.ImageView;

public class ProductTable {

	private String name;
	private String price;
	private ImageView image;

	public ProductTable() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}
}
