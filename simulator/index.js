function generateReference() {
    var question1Value = document.getElementById("question1").value;
    var question2Value = document.getElementById("question2").value;
    var question3Value = document.getElementById("question3").value;
  
    // Obtenez le premier caractère de chaque choix
    var reference = question1Value.charAt(0) + question2Value.charAt(0) + question3Value.charAt(0);
  
    // Ajoutez d'autres questions si nécessaire
  
    document.getElementById("reference").innerHTML = "Référence : " + reference;
  }
  