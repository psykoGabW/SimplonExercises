var elem = document.querySelector('#menu');
var instance = M.Sidenav.init(elem);

function integrateHtmlFile(file)
{
      document.getElementById("htmlFileContent").innerHTML="<object type='text/htmlFileContent' data='"+file+"'/>";
}

function closeNav(){
  instance.close();
}
