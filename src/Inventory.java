import java.util.*;


class Inventory {
    Map<Integer, Product> products = new HashMap<>();
    Map<Integer, String> categories = new HashMap<>();

    Inventory() {
        categories.put(1, "Computadores");
        categories.put(2, "Celulares");
        categories.put(3, "Electrodomésticos");
        categories.put(4, "TV");
        categories.put(5, "Accesorios");
        categories.put(6, "Videojuegos");
        categories.put(7, "Audio y video");
    }

    public boolean isValidCategory(int category) {
        return categories.containsKey(category);
    }
    public boolean categoryExists(String categoryName) {
        return categories.containsValue(categoryName);
    }
    public boolean isValidProduct(int referenceNumber) {
        return products.containsKey(referenceNumber);
    }

    void addProduct(Product product) throws Exception {
        if (products.containsKey(product.referenceNumber)) {
            throw new Exception("\n---> Ya existe un producto con la referencia "+product.referenceNumber+" en el inventario\n\n");
        }
        products.put(product.referenceNumber, product);
    }

    void decreaseProduct(int referenceNumber, int quantity) throws Exception {
        Product product = products.get(referenceNumber);
        if (product == null) {
            throw new Exception("\n---> No existe el producto con la referencia "+referenceNumber+" en el inventario\n\n");
        }
        if (product.quantity < quantity) {
            throw new Exception("\n---> No hay suficiente stock del producto "+product.name+" para disminuir la cantidad\n\n");
        }
        product.quantity -= quantity;
    }

    void removeProduct(int referenceNumber) {
        products.remove(referenceNumber);
    }

    void addCategory(String name) throws Exception {
        int number = categories.size() + 1;
        categories.put(number, name);
    }

    void removeCategory(int number) {
        categories.remove(number);
    }

    void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("\n---> No hay Productos En el inventario por el momento");
        } else {
            System.out.println("=".repeat(10)+"  Inventario  "+"=".repeat(10)+"\n");
            for (Product product : products.values()) {
                System.out.println("-+-".repeat(10));
                System.out.println("Nombre: "+ product.name);
                System.out.println("Precio: "+ product.price);
                System.out.println("Referencia: "+ product.referenceNumber);
                System.out.println("Cantidad: "+ product.quantity);
                System.out.println("Categoria: "+ categories.get(product.category));
            }
        }
    }

    void displayCategories() {
        if (categories.isEmpty()) {
            System.out.println("\n---> No hay categorias Disponibles por el momento");
        } else {
            System.out.println("=".repeat(10)+"  Categorias  "+"=".repeat(10));
            for (Map.Entry<Integer, String> entry : categories.entrySet()) {
                System.out.println(entry.getKey()+". "+entry.getValue());
            }
        }
    }
}