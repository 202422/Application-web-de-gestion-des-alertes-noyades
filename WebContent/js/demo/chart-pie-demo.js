// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Récupérer les labels et données à partir de la variable `feedbackStats`
var labels = Object.keys(feedbackStats);
var data = Object.values(feedbackStats);

// Pie Chart Example
var ctx = document.getElementById("myUniqueChart");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: labels, // Utilisation des labels dynamiques
    datasets: [{
      data: data, // Utilisation des données dynamiques
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'], // Personnalisez les couleurs ici
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80, // Contrôle la taille du trou central
  },
});
