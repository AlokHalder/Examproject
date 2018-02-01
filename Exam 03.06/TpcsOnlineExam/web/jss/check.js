/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var input=/^[A-D]+/;
function checkQuestion()
{
    if(document.getElementById("q").value=='')
    {
        alert("Please enter a valid  Question");
        return false;
    }
    else if(document.getElementById("4").value=='')
    {
        alert("Please enter a valid  option 1");
        return false;
    }
    else if(document.getElementById("4").value=='')
    {
        alert("Please enter a valid  option 2");
        return false;
    }
    else if(document.getElementById("5").value=='')
    {
        alert("Please enter a valid  option 2");
        return false;
    }
    else if(document.getElementById("6").value=='')
    {
        alert("Please enter a valid  option 3");
        return false;
    }
    else if(document.getElementById("7").value=='')
    {
        alert("Please enter a valid  option 4");
        return false;
    }
    else if(!input.test(document.getElementById("one").value))
    {
        alert("Please enter a valid currect option(A TO D)");
        return false;
    }                    
}

var pattern=/^[0-9]+/;
var pattern1=/^[A-Za-z]+/;
var pattern2=/^[a-zA-Z0-9][a-zA-Z0-9-_.\s]+@[a-zA-Z0-9-\s].+\.[a-zA-Z]{2,5}$/
var ob;
function checkRegistration()
{
    if(document.getElementById("name").value=='')
    {
        alert("Please enter Your name")
        return false;
    }
    else  if(pattern.test(document.getElementById("name").value))
    {
        alert("Name should not contain number");
        return false;
                       
    }
    else if(!pattern1.test(document.getElementById("name").value))
    {
        alert("Name should not contain special chars");
        return false;
                       
    }
    else if(document.getElementById("mobile").value=='')
    {
        alert("Please enter Your Mobile")
        return false;
    }
    else  if(!pattern.test(document.getElementById("mobile").value))
    {
        alert("Mobile should contain only number");
        return false;
                       
    }
    else if((document.getElementById("mobile").value.length) <10)
    {
        alert("Mobile should contain 10 digits");
        return false;
                       
    }
    else  if(!pattern2.test(document.getElementById("email").value))
    {
        alert("Please Enter Valid Email");
        return false;
                       
    }
    else if(document.getElementById("examieeAddress").value=='')
    {
        alert("Please enter Your Adderss")
        return false;
    }
    else if(document.getElementById("examieeDegree").value=='')
    {
        alert("Please enter Your Degree")
        return false;
    }
    else if(document.getElementById("examieeExp").value=='')
    {
        alert("Please Enter Your Experience")
        return false;
    }
}
            
function submitData(){
    var examineeID=document.examinee.examineeId.value;
    var value="Details";
    alert("Are you want to show the details of examinee: "+examineeID);
    document.examinee.action="ExamineeApplicationServlet?submit="+value;
    document.examinee.method="post";
    document.examinee.submit();             
}

function confirmStopExam(){
    var stopExam = confirm("Are you sure to end your exam ?");
    if(stopExam ==true){
        return true;
    }else{
        return false;
    }
}
function paginationQuestion(questionNo){
    document.forma.action="ExamineeApplicationServlet?action=questionPagination&questionNo="+questionNo;
    document.forma.method="post";
    document.forma.submit();
}