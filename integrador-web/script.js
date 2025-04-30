const API_PRODUCTOS = 'http://localhost:8081/api/productos';
const API_FACTURAS = 'http://localhost:8080/api/facturas';

// Crear Producto
document.getElementById('crearProducto').addEventListener('click', async () => {
    const nombreProducto = document.getElementById('nombreProducto').value.trim();
    const descripcionProducto = document.getElementById('descripcionProducto').value.trim();
    const precioProducto = document.getElementById('precioProducto').value.trim();
    const clienteProducto = document.getElementById('clienteProducto').value.trim();

    if (!nombreProducto || !descripcionProducto || !precioProducto || isNaN(precioProducto) || precioProducto <= 0 || isNaN(clienteProducto)) {
        alert("Por favor, completa todos los campos correctamente.");
        return;
    }

    try {
        const producto = {
            nombre: nombreProducto,
            descripcion: descripcionProducto,
            precio: parseFloat(precioProducto),
            cliente: {
                id: parseInt(clienteProducto)
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
            await listarProductos();  // Asegura que esperas a que se actualice la lista
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
        } else {
            console.error("Error al obtener productos");
        }
    } catch (error) {
        console.error("Error de conexión al listar productos:", error);
    }
// Crear Factura
document.getElementById('crearFactura').addEventListener('click', async () => {
    const totalFactura = document.getElementById('totalFactura').value.trim();

    if (!totalFactura || isNaN(totalFactura) || totalFactura <= 0) {
        alert("Por favor, ingresa un total válido.");
        return;
    }

    try {
        const factura = {
            total: parseFloat(totalFactura)
        };

        const response = await fetch(API_FACTURAS, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(factura)
        });

        if (response.ok) {
            alert("Factura creada con éxito");
            await listarFacturas(); // <- Mostrar en pantalla
        } else {
            alert("Error al crear la factura");
        }
    } catch (error) {
        console.error("Error al crear la factura:", error);
        alert("Error en la conexión al crear factura");
    }
});

// Listar Facturas
async function listarFacturas() {
    try {
        const response = await fetch(API_FACTURAS);
        if (response.ok) {
            const facturas = await response.json();
            const lista = document.getElementById('listaFacturas');
            lista.innerHTML = "";

            facturas.forEach(factura => {
                const item = document.createElement('li');
                item.textContent = `Factura ID: ${factura.id} - Total: $${factura.total}`;
                lista.appendChild(item);
            });
        } else {
            alert("Error al obtener facturas");
        }
    } catch (error) {
        console.error("Error al listar facturas:", error);
        alert("Error al mostrar las facturas");
    }
}

document.getElementById('asignarProductoFactura').addEventListener('click', async () => {
    const facturaId = document.getElementById('facturaId').value.trim();
    const productoId = document.getElementById('productoId').value.trim();
    const resultado = document.getElementById('resultadoAsignacion');

    if (!facturaId || !productoId || isNaN(facturaId) || isNaN(productoId)) {
        alert("Por favor, ingresa IDs válidos para factura y producto.");
        return;
    }

    try {
        const url = `http://localhost:8080/api/facturas/${facturaId}/productos/${productoId}`;
        const response = await fetch(url, { method: 'POST' });

        if (response.ok) {
            resultado.textContent = `✅ Producto ${productoId} asignado a la factura ${facturaId} correctamente.`;
            await listarFacturas(); // Opcional: actualizar lista
        } else {
            resultado.textContent = `❌ Error al asignar el producto.`;
        }
    } catch (error) {
        console.error("Error al asignar producto a factura:", error);
        resultado.textContent = "❌ Error de conexión al asignar producto.";
    }
});



}
