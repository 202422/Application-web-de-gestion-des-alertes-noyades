<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashbord.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Gestion des alertes</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item ">
        <a class="nav-link" href="statistiquesAlertesNoyade">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Gestion des alertes
    </div>

    <!-- Nav Item - Traitement des alertes -->
    <li class="nav-item">
        <a class="nav-link" href="traiter%20alertes.jsp">
            <i class="fas fa-fw fa-bell-slash"></i>
            <span>Traitement des alertes</span>
        </a>
    </li>

    <!-- Nav Item - Liste des alertes -->
    <li class="nav-item">
        <a class="nav-link" href="AlerteNoyadeServlet2">
            <i class="fas fa-fw fa-tint"></i> <!-- Icône pour les alertes noyade -->
            <span>Alerte Noyade</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="EquipeServlet?action=list">
            <i class="fas fa-fw fa-battery-quarter"></i> <!-- Icône pour alerte batterie faible -->
            <span>Alerte Batterie Faible</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Gestion des équipes
    </div>

    <!-- Nav Item - Gestion des équipes -->
    <li class="nav-item">
        <a class="nav-link" href="EquipeServlet?action=list">
            <i class="fas fa-fw fa-users"></i>
            <span>Consulter les équipes</span>
        </a>
    </li>

    <!-- Nav Item - Ajouter un membre à l'équipe -->
    <li class="nav-item">
        <a class="nav-link" href="PersonneServlet">
            <i class="fas fa-fw fa-user-plus"></i>
            <span>Ajouter un membre</span>
        </a>
    </li>

    <!-- Nav Item - Liste des équipes occupées -->
    <li class="nav-item">
        <a class="nav-link" href="equipesEnSauvetage">
            <i class="fas fa-fw fa-clock"></i> <!-- Icône pour équipe occupée -->
            <span>Équipes occupées</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Gestion des drones
    </div>

    <!-- Nav Item - Gestion des drones -->
    <li class="nav-item">
        <a class="nav-link" href="DroneServlet?action=list">
            <i class="fas fa-fw fa-robot"></i>
            <span>Liste des drones</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="DroneServlet?action=addForm">
            <i class="fas fa-fw fa-plus-circle"></i>
            <span>Ajouter un drone</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Addons
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
            aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-fw fa-folder"></i>
            <span>Pages</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Login Screens:</h6>
                <a class="collapse-item" href="login.html">Login</a>
                <a class="collapse-item" href="register.html">Register</a>
                <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Other Pages:</h6>
                <a class="collapse-item" href="404.html">404 Page</a>
                <a class="collapse-item" href="blank.html">Blank Page</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="charts.html">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Charts</span>
        </a>
    </li>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="tables.html">
            <i class="fas fa-fw fa-table"></i>
            <span>Tables</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->
