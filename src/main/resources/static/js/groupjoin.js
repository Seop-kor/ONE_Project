function ok(e){
    const num = e.id.toString().replace('ok_group_', '');
    $.ajax({
        type: 'get',
        url: '/admin/ok',
        data: {
            pk : num
        }
    }).done(function (data) {
        if(data === true){
            $('#' + num).remove();
        }
    });
}

function sorry(e) {
    const num = e.id.toString().replace('sorry_group_', '');
    $.ajax({
        type: 'get',
        url: '/admin/sorry',
        data: {
            pk : num
        }
    }).done(function (data) {
        if(data === true){
            $('#' + num).remove();
        }
    });
}