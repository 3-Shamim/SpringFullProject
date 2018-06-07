$(document).ready(function () {

    var temp = "";

    /*$("#registration").click(function () {
        $("#exampleModal").modal();
    })*/

    /*$("#editAddressBook").click(function () {
        $("#editAddressBookModal").modal();
    })*/

    $("#sweetModal").click(function () {
        alert("ok")
    })

    /*$("#searchUser").keyup(function () {

        var name = $("#searchUser").val();

        temp = temp + name;

        window.location.href = '/users?name=' + temp;
    })*/

})

function delAddressBook() {
    var delId = $("#delId").val();
    swal(
        {title: 'Are you sure?', showCancelButton: true},
        function (isConfirm) {
            if (isConfirm) {
                window.location.href = '/addressDelete/' + delId;
            } else {
                window.location.href = '/addresses';
            }
        }
    )
}
