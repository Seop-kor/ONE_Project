$('#give_admin_btn').on('click', function () {
    $.ajax({
        method: 'post',
        url: '/admin/give',
        data: {
            id: document.getElementById('give_admin_id').value
        }
    }).done(function (data) {
        if(data === true){
            alert("어드민 등록을 완료했습니다.");
            $("form").each(function () {
                if(this.id === "adminForm"){
                    this.reset();
                }
            });
        }
    });
});