function isChecked(event){
    if(event.getAttribute('checked') === null){
        event.setAttribute('checked','');
    }
    else{
        event.removeAttribute('checked');
    }
}

function isSelected(event){
    if(event.getAttribute('selected') === null){
        event.setAttribute('selected','selected');
    }
    else{
        event.removeAttribute('selected');
    }
  // document.getElementById(id).selected=true;


}

$(document).ready(function(){

    $("body").on("click","#addModule",function(){

        $("#myModal").modal("show");

        $(".wrapper").addClass("after_modal_appended");

        //appending modal background inside the blue div
        $('.modal-backdrop').appendTo('.blue');

        //remove the padding right and modal-open class from the body tag which bootstrap adds when a modal is shown

        $('body').removeClass("modal-open");
        $('body').css("padding-right","");
    });

});



function enable(){

    document.getElementById("").display = "block";
    document.getElementById("discount").disabled = false;
}
function disable(){
    document.getElementById("").display = "none";
    document.getElementById("discount").disabled = true;
}

function printDiv(divName) {

    var printContents = document.getElementById(divName).innerHTML;

    /*  var originalContents = document.body.innerHTML;
      //   var originalContents = document.getElementById("originalPage");

      document.body.innerHTML = printContents;

      window.print();

      document.body.innerHTML = originalContents;

*/

    //var newWin=window.open('','Print-Window');
    var newWin=window.open('','Print-Window');

    newWin.document.open();

    newWin.document.write('<html><body onload="window.print()">'+printContents+'</body></html>');

    newWin.document.close();


}