$('#homework_save_btn').on('click', function () {
    $.ajax({
        method: 'post',
        url: '/homework_saveing',
        data: {
            homework_title : document.getElementById('homework_title').value,
            homework_start : new Date().toISOString().replace('Z',''),
            homework_end : document.getElementById('homework_end').value,
            homework_content : document.getElementById('homework_content').value,
            receive_mail : document.getElementById('receive_mail').value
        }
    }).done(function (data) {
        if(data === true){
            alert("과제 등록이 완료됐습니다.");
            $("form").each(function () {
                if(this.id === "homeworkForm"){
                    this.reset();
                }
            });
        }
    });
});