// Constantes API
API_USUARIO_URL = "http://localhost:8080/api/beta-1.0/usuarios";
API_EMPLEADO_URL = API_USUARIO_URL+"/empleados";
API_GERENTE_URL = API_USUARIO_URL+"/g-departamento";
API_DEPARTAMENTO_URL = "http://localhost:8080/api/beta-1.0/departamentos"
API_PRESUPUESTO_URL = API_DEPARTAMENTO_URL + "/id/{id_departamento}/presupuestos";

document.addEventListener('DOMContentLoaded', function() {
    let usuario_activo = null; // Variable para almacenar los datos del usuario
    
    // Obtener información del usuario desde localStorage
    const email = localStorage.getItem("emailUsuario");
    
    fetch(`${API_USUARIO_URL}/email/${email}`)
        .then(response => response.json())
        .then(usuario => {
            if (!usuario) {
                window.location.href = 'login.html';
                return;
            }
            
            usuario_activo = usuario; // Almacenar el usuario globalmente
            
            // Mostrar información del usuario
            document.getElementById('current-user').textContent = usuario.email;
            document.getElementById('welcome-user').textContent = `${usuario.nombre} ${usuario.apellido}`;
            document.getElementById('user-department').textContent = usuario.departamento.nombre;
            
            localStorage.setItem("departamento", usuario.departamento.id);
            
            // Cargar datos del presupuesto después de tener el usuario
            cargarPresupuesto(localStorage.getItem("departamento"));
            loadExpenseHistory();
        })
        .catch(error => {
            console.error("Error al cargar usuario:", error);
            window.location.href = 'login.html';
        });
    
    // Función para cargar datos del presupuesto
    async function cargarPresupuesto(id_departamento) {
        try {
            const response = await fetch(`${API_DEPARTAMENTO_URL}/id/${id_departamento}/presupuestos`);
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            
            const data = await response.json();
            
            // Calcular porcentaje utilizado
            const porcentajeUtilizado = (data.presupuestoUtilizado / data.presupuestoTotal) * 100;
            const claseProgress = porcentajeUtilizado > 80 ? 'progress-danger' : 
                                porcentajeUtilizado > 50 ? 'progress-warning' : 'progress-bar';
            
            // Actualizar resumen en welcome-section
            const welcomeBudgetHTML = `
                <div class="budget-summary">
                    <div class="budget-card">
                        <h3>Presupuesto Total</h3>
                        <p class="amount">$${data.presupuestoTotal.toLocaleString('es-ES', {minimumFractionDigits: 2})}</p>
                    </div>
                    <div class="budget-card">
                        <h3>Utilizado</h3>
                        <p class="amount expense">$${data.presupuestoUtilizado.toLocaleString('es-ES', {minimumFractionDigits: 2})}</p>
                        <div class="progress-container">
                            <div class="${claseProgress}" style="width: ${porcentajeUtilizado}%">
                                ${porcentajeUtilizado.toFixed(2)}%
                            </div>
                        </div>
                    </div>
                </div>
            `;
            
            // Insertar después del último párrafo en welcome-section
            const welcomeSection = document.getElementById('welcome-section');
            const lastParagraph = welcomeSection.querySelector('p:last-of-type');
            lastParagraph.insertAdjacentHTML('afterend', welcomeBudgetHTML);
            
            // Actualizar sección de presupuestos
            const proyectosHTML = await cargarProyectos(id_departamento);
            
            document.getElementById('budgets-section').innerHTML = `
                <div class="card">
                    <h2>Presupuesto del Departamento</h2>
                    <div class="budget-summary">
                        <div class="budget-card">
                            <h3>Presupuesto Total</h3>
                            <p class="amount">$${data.presupuestoTotal.toLocaleString('es-ES', {minimumFractionDigits: 2})}</p>
                        </div>
                        <div class="budget-card">
                            <h3>Balance Actual</h3>
                            <p class="amount">$${(data.presupuestoTotal - data.presupuestoUtilizado).toLocaleString('es-ES', {minimumFractionDigits: 2})}</p>
                            <div class="progress-container">
                                <div class="${claseProgress}" style="width: ${porcentajeUtilizado}%">
                                    ${porcentajeUtilizado.toFixed(2)}% utilizado
                                </div>
                            </div>
                        </div>
                    </div>
                    <h3>Proyectos Asociados</h3>
                    <div class="project-list">
                        ${proyectosHTML}
                    </div>
                </div>
            `;
            
        } catch (error) {
            console.error("Error al cargar presupuesto:", error);
            alert("Error al cargar presupuesto");
            return;
        }
    }

    // Funcion para cargar proyectos
    async function cargarProyectos(id_departamento) {
        const response = await fetch(`${API_DEPARTAMENTO_URL}/id/${id_departamento}/proyectos`); 

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        cargarSelect(data);
        
        const proyectosHTML = data.map(p => {
            const gastoTotal = (p.gastos ?? [])
            .filter(g => g.estado === 'APROBADO')
            .reduce((acc, g) => acc + (g.monto ?? 0), 0);
            return `
                <div class="project-item">
                    <span><strong>${p.nombre}</strong></span>
                    <span class="expense">$${gastoTotal.toLocaleString('es-ES', {minimumFractionDigits: 2})}</span>
                </div>
            `}).join('');
        return proyectosHTML
    }

    // Configurar navegación
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', async function() {
            // Remover clase active de todos los items
            document.querySelectorAll('.nav-item').forEach(i => i.classList.remove('active'));
            // Agregar clase active al item clickeado
            this.classList.add('active');
            
            // Ocultar todas las secciones
            document.querySelectorAll('.main-content > div').forEach(div => {
                div.classList.add('hidden');
            });
            
            // Mostrar la sección correspondiente
            const sectionId = this.getAttribute('data-section') + '-section';
            document.getElementById(sectionId).classList.remove('hidden');
            if(sectionId == 'budgets'){
                cargarPresupuesto(localStorage.getItem("departamento"));
            }else if(sectionId == 'expense-history'){
                loadExpenseHistory();
            }
        });
    });
    
    // Configurar logout
    document.getElementById('logout-btn').addEventListener('click', function() {
        localStorage.removeItem('emailUsuario');
        localStorage.removeItem('departamento');
        window.location.href = 'login.html?logout=true';
    });
    
    // Configurar filtros de gastos
    document.getElementById('expense-type-filter').addEventListener('change', loadExpenseHistory);
    document.getElementById('expense-month-filter').addEventListener('change', loadExpenseHistory);
    document.getElementById('expense-year-filter').addEventListener('change', loadExpenseHistory);
    
    // Configurar formulario de solicitud
    document.getElementById('expense-request-form').addEventListener('submit', function(e) {
        e.preventDefault();
        submitExpenseRequest();
    });
    
    // Función para cargar el historial de gastos
    function loadExpenseHistory() {
        if (!usuario_activo) return;
        
        const typeFilter = document.getElementById('expense-type-filter').value;
        const monthFilter = document.getElementById('expense-month-filter').value;
        const yearFilter = document.getElementById('expense-year-filter').value;

        fetch(`/api/beta-1.0/departamentos/id/${localStorage.getItem("departamento")}/gastos`)
            .then(response => response.json())
            .then(gastos => {
                let filteredExpenses = gastos;
                
                if (typeFilter !== 'all') {
                    filteredExpenses = filteredExpenses.filter(expense => {
                        if (typeFilter === 'pending') {
                            return expense.estado === 'PENDIENTE';
                        }else if(typeFilter === 'aproved'){
                            return expense.estado === 'APROBADO';
                        }else if(typeFilter === 'rejected'){
                            return expense.estado === 'RECHAZADO';
                        }
                    });
                }
                
                if (monthFilter !== 'all') {
                    filteredExpenses = filteredExpenses.filter(expense => {
                        const date = new Date(expense.fecha);
                        return (date.getMonth() + 1) === parseInt(monthFilter);
                    });
                }
                
                if (yearFilter) {
                    filteredExpenses = filteredExpenses.filter(expense => {
                        const date = new Date(expense.fecha);
                        return date.getFullYear() === parseInt(yearFilter);
                    });
                }
                
                // Ordenar por fecha (más reciente primero)
                filteredExpenses.sort((a, b) => new Date(b.fecha) - new Date(a.fecha));
                
                // Mostrar en la tabla
                renderExpensesTable(filteredExpenses);
            })
            .catch(error => {
                console.error("Error al cargar gastos:", error);
                const departmentExpenses = getDepartmentExpenses(usuario_activo.departamento.nombre);
                renderExpensesTable(departmentExpenses);
            });
    }
    
    async function renderExpensesTable(expenses) {
        const tbody = document.querySelector('#expenses-table tbody');
        tbody.innerHTML = '';
        
        const noExpensesMsg = document.getElementById('no-expenses');
        
        if (!expenses || expenses.length === 0) {
            noExpensesMsg.style.display = 'block';
            return;
        }
        
        noExpensesMsg.style.display = 'none';
        
        expenses.forEach(expense => {
            const row = document.createElement('tr');
            
            // Formatear fecha
            const date = new Date(expense.fecha || expense.date);
            const formattedDate = date.toLocaleDateString('es-ES');
            
            // Determinar clase para el monto según el tipo
            let amountClass = '';
            if (expense.estado === 'PENDIENTE' || expense.status === 'pending') {
                amountClass = 'pending';
            }
            
            // Determinar texto de estado
            let statusText = '';
            switch (expense.estado) {
                case 'PENDIENTE':
                    statusText = 'Pendiente';
                    break;
                case 'APROBADO':
                    statusText = 'Aprobado';
                    break;
                case 'RECHAZADO':
                    statusText = 'Rechazado';
                    break;
                default:
                    statusText = expense.estado;
            }
            
            row.innerHTML = `
                <td>${formattedDate}</td>
                <td>${expense.justificacion}</td>
                <td>${expense.proyecto?.nombre || "Proyecto N/D"}</td>
                <td class="${amountClass}">-$${(expense.monto || expense.amount).toFixed(2)}</td>
                <td>${statusText}</td>
                <td></td> <!-- Botones -->
            `;
            const buttonCell = row.querySelector('td:last-child');

            if (expense.estado === 'PENDIENTE') {
                const btnApprove = document.createElement('button');
                btnApprove.textContent = 'Aprobar';
                btnApprove.classList.add('btn-approve');
                btnApprove.dataset.id = expense.id;
                btnApprove.addEventListener('click', () => {
                    actualizar_estado_gasto(expense.id, 'APROBADO');
                });

                const btnReject = document.createElement('button');
                btnReject.textContent = 'Rechazar';
                btnReject.classList.add('btn-reject');
                btnReject.dataset.id = expense.id;
                btnReject.addEventListener('click', () => {
                    actualizar_estado_gasto(expense.id, 'RECHAZADO');
                });

                buttonCell.appendChild(btnApprove);
                buttonCell.appendChild(btnReject);
            } else if (expense.estado === 'APROBADO') {
                const btnReject = document.createElement('button');
                btnReject.textContent = 'Rechazar';
                btnReject.classList.add('btn-reject');
                btnReject.dataset.id = expense.id;
                btnReject.addEventListener('click', () => {
                    actualizar_estado_gasto(expense.id, 'RECHAZADO');
                });
                buttonCell.appendChild(btnReject);
            }

            tbody.appendChild(row);
        });
    }

    function actualizar_estado_gasto(idGasto, nuevoEstado) {
        const idDepartamento = localStorage.getItem("id_departamento");

        fetch(`/api/beta-1.0/departamentos/id/${idDepartamento}/gastos/${idGasto}/estado/${nuevoEstado}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) throw new Error("No se pudo actualizar el estado");
            return response.text();
        })
        .then(msg => {
            alert(msg);
            loadExpenseHistory();
        })
        .catch(err => {
            console.error(err);
            alert("Error al actualizar el estado del gasto.");
        });
        cargarPresupuesto(idDepartamento);
    }

    function cargarSelect(proyectos) {
        const select = document.getElementById("expense-project");
        select.innerHTML=`<option value="">Seleccione un proyecto</option>`;
        proyectos.forEach(p => {
            const option = document.createElement('option');
            option.value = p.id;
            option.textContent = p.nombre;
            select.appendChild(option);
        });
    }
    
    async function submitExpenseRequest() {
        if (!usuario_activo) return;

        const amount = parseInt(document.getElementById('expense-amount').value);
        const justification = document.getElementById('expense-description').value;
        const projectId = document.getElementById('expense-project').value;

        // Validación básica
        if (isNaN(amount) || amount <= 0) {
            showRequestError('Por favor ingrese un monto válido');
            return;
        }

        if (!projectId) {
            showRequestError('Seleccione un proyecto');
            return;
        }

        if (justification.length < 80 || justification.length > 500) {
            showRequestError('La justificación debe tener entre 80 y 500 caracteres');
            return;
        }

        const today = new Date();
        const fechaFormateada = today.toISOString().split("T")[0]; // yyyy-MM-dd

        console.log({
            monto: amount,
            fecha: fechaFormateada,
            justificacion: justification,
            proyecto: { id: parseInt(projectId) },
            registrador: { id: usuario_activo.id }
        });

        try {
            const response = await fetch(`${API_DEPARTAMENTO_URL}/id/${localStorage.getItem("departamento")}/gastos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    monto: amount,
                    fecha: fechaFormateada,
                    justificacion: justification,
                    proyecto: { id: parseInt(projectId) },
                    registrador: { id: usuario_activo.id }
                })
            });

            if (!response.ok) throw new Error("Error en la solicitud");

            document.getElementById('request-success').style.display = 'block';
            document.getElementById('request-error').style.display = 'none';
            document.getElementById('expense-request-form').reset();
            loadExpenseHistory()
        } catch (error) {
            console.error(error);
            showRequestError("Error al enviar la solicitud. Intente nuevamente.");
        }
    }
    
    function showRequestError(message) {
        const errorElement = document.getElementById('request-error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
        document.getElementById('request-success').style.display = 'none';
    }
});