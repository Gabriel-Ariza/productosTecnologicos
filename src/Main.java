import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = null;


        try {
            scanner = new Scanner(System.in);
            while (true) {
                System.out.println("-".repeat(30));
                System.out.println("\tMenu Principal");
                System.out.println("-".repeat(30));
                System.out.println("1. Añadir Producto");
                System.out.println("2. Disminuir C. Producto");
                System.out.println("3. Eliminar Producto");
                System.out.println("4. Añadir Categoria");
                System.out.println("5. Eliminar Categoria");
                System.out.println("6. Mostrar Inventario");
                System.out.println("7. Mostrar Categorias");
                System.out.println("8. Salir");
                System.out.println("-".repeat(30) + "\n");
                System.out.print("Ingrese una opción: ");
                int option = scanner.nextInt();
    
                try {
                    switch (option) {
                        case 1:
                            System.out.print("Nombre Producto: ");
                            String name = scanner.next();
                            System.out.print("Precio: ");
                            double price = scanner.nextDouble();
                            System.out.print("Numero Referencia: ");
                            int referenceNumber = scanner.nextInt();
                            System.out.print("Stock Producto: ");
                            int quantity = scanner.nextInt();
                            int category;
                            do {
                                try {
                                    System.out.print("\n");
                                    inventory.displayCategories();
                                    System.out.print("\nEscoja la Categoria del nuevo producto: ");
                                    category = scanner.nextInt();
                            
                                    if (inventory.isValidCategory(category)) {
                                        break;
                                    } else {
                                        System.out.println("---> Categoria invalida. Elija una de la Lista");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("---> Dato invalido. Ingrese un numero");
                                    scanner.next();
                                }
                            } while (true);
                            inventory.addProduct(new Product(name, price, referenceNumber, quantity, category));
                            System.out.println("\nProducto "+name+" Creado exitosamente\n\n");
                            break;
                        case 2:
                            System.out.print("Numero Referencia: ");
                            referenceNumber = scanner.nextInt();
                            if (!inventory.isValidProduct(referenceNumber)) {
                                System.out.println("\n---> El producto no existe\n\n");
                                break;
                            }
                            System.out.print("Nuevo stock del Producto: ");
                            quantity = scanner.nextInt();
                            inventory.decreaseProduct(referenceNumber, quantity);
                            System.out.println("\nProducto Actualizado exitosamente\n\n");
                            break;
                        case 3:
                            System.out.print("Numero Referencia: ");
                            referenceNumber = scanner.nextInt();
                            if (!inventory.isValidProduct(referenceNumber)) {
                                System.out.println("El producto no existe.");
                                break;
                            }
                            inventory.removeProduct(referenceNumber);
                            System.out.println("\nProducto Eliminado exitosamente\n\n");
                            break;
                        case 4:
                            System.out.print("Nombre Nueva Categoria: ");
                            String categoryName = scanner.next();
                            if (inventory.categoryExists(categoryName)) {
                                System.out.println("La categoria ya existe.");
                                break;
                            }
                            inventory.addCategory(categoryName);
                            System.out.println("\nCategoria Creada exitosamente\n\n");
                            break;
                        case 5:
                        int itemCategory;
                        do {
                            try {
                                inventory.displayCategories();
                                System.out.print("Categoria a eliminar: ");
                                itemCategory = scanner.nextInt();
                        
                                if (inventory.isValidCategory(itemCategory)) {
                                    break;
                                } else {
                                    System.out.println("---> Categoria invalida. Elija una de la Lista");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("---> Dato invalido. Ingrese un numero");
                                scanner.next();
                            }
                        } while (true);
                            inventory.removeCategory(itemCategory);
                            System.out.println("\nCategoria Eliminada exitosamente\n\n");
                            break;
                        case 6:
                            inventory.displayInventory();
                            System.out.println("\n\n");
                            break;
                        case 7:
                            inventory.displayCategories();
                            System.out.println("\n\n");
                            break;
                        case 8:
                            System.exit(0);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n\n---> opcion invalida. Escoja una de la lista\n");
                    scanner.next(); // discard the invalid input
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }


        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }



    }
}