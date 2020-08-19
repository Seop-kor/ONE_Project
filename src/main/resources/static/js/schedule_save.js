$('#schedule_btn').on('click', function () {
    $.ajax({
       type: 'post',
       url: '/schedule_register_save',
       data:{
           date : $('#date').val(),
           title : $('#schedule_title').val()
       }
    }).done(function (data) {
        if(data === true){
            alert("일정 등록 완료");
            $("form").each(function () {
                if(this.id === "scheduleForm"){
                    this.reset();
                }
            });
        }
    });
});