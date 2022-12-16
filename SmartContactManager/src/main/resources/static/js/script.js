console.log("this is script file...")

 toggleSidebar=()=>{
if($(".sidebar").is(":visible")){
//if true please close it

$(".sidebar").css("display", "none");
$(".content").css("margin-left","0%");
}
else{

    //if false please show it
    $(".sidebar").css("display","block");
$(".content").css("margin-left","20%");

}
};