let index = {
    init: function () {
        $("btn-save").on("click",()=>{
            this.save();
        });
    },

    save:function () {
        let data = {
            title: $("title").val(),
            content: $("content").val()
        };
        $.ajax({
            type:"POST",
            url:"/api/v1/post",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            data_type:"json"
        }).done(function (resp) {
            alert("글쓰기 완료되었습니다.");
            window.location.href="/board/list";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();