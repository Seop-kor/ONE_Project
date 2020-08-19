const file_upload = document.querySelector('#upload_file');
let fileinfo = null;
file_upload.addEventListener('change', handleFile, false);

function handleFile(e){
    fileinfo = e.target.files[0];
}

$('#upload_btn').on('click', function () {
    const fileform = new FormData();
    fileform.append('file', fileinfo);
    $.ajax({
        type : 'post',
        url : '/file',
        data : fileform,
        processData: false,
        contentType: false
    }).done(function (data) {
        if(data === "ok"){
            $.ajax({
               type: 'post',
               url : '/upload',
               data: {
                   data_title : $('#uploadForm [id="title"]').val(),
                   data_content : $('#uploadForm [id="content"]').val()
               }
            }).done(function (last) {
                alert("업로드가 완료 되었습니다.");
                $("form").each(function () {
                    if(this.id === "uploadForm"){
                        this.reset();
                    }
                });
            });
        }
    });
});