const API_PRODUCTOS = 'http://localhost:8081/api/productos';
const API_FACTURAS = 'http://localhost:8080/api/facturas';

// Crear Producto
document.getElementById('crearProducto').addEventListener('click', async () => {
    const nombreProducto = document.getElementById('nombreProducto').value.trim();
    const descripcionProducto = document.getElementById('descripcionProducto').value.trim();
    const precioProducto = document.getElementById('precioProducto').value.trim();
    const clienteProducto = document.getElementById('clienteProducto').value.trim(); // primero como string

    // Validamos si falta algo (clienteProducto puede ser 0 y aún ser válido)
    if (!nombreProducto || !descripcionProducto || !precioProducto || !clienteProducto) {
        alert("Por favor, completa todos los campos.");
        return;
    }

    try {
        const producto = {
            nombre: nombreProducto,
            descripcion: descripcionProducto,
            precio: parseFloat(precioProducto), // asegúrate que sea número decimal
            cliente: {
                id: parseInt(clienteProducto) // lo convertimos aquí
            }
        };

        const response = await fetch(API_PRODUCTOS, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(producto)
        });

        if (response.ok) {
            alert("Producto creado con éxito");
            listarProductos();  // Refresca la lista de productos
        } else {
            alert("Error al crear el producto");
        }
    } catch (error) {
        console.error(error);
        alert("Error en la conexión");
    }
});


// Listar Productos
async function listarProductos() {
    try {
        const response = await fetch(API_PRODUCTOS);
        if (response.ok) {
            const productos = await response.json();
            const lista = document.getElementById('listaProductos');
            lista.innerHTML = "";

            productos.forEach(producto => {
                const item = document.createElement('li');
                item.textContent = `Producto: ${producto.nombre} - Descripción: ${producto.descripcion} - Precio: $${producto.precio}`;
                lista.appendChild(item);
            });
        }
    } catch (error) {
        console.error(error);
    }
}
