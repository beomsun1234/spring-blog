let index = {
    init: function () {
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-replySave").on("click",()=> {
            this.replySave();
        });
    },

    save:function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            author : $("#author").val(),
            memberId : $("#memberId").val()
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

    update:function () {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            type:"PUT",
            url:"/api/v1/post/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            data_type:"json"
        }).done(function (resp) {
            alert("수정이 완료되었습니다.");
            window.location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replySave:function () {
        let id = $("#board-id").val();
        alert(id);
        let data = {
            content: $("#reply-content").val(),
            postId : id,
            memberId : $("#member-id").val()
        };
        alert(data.content);
        $.ajax({
            type:"POST",
            url: "http://localhost:8080/api/v1/post/"+id+"/reply",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            data_type:"json"
        }).done(function (resp) {
            alert("리플작성 완료되었습니다.");
            window.location.href="/board/detail/"+id;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();