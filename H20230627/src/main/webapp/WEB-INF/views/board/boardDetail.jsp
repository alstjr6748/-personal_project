<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <h3>상세화면입니다.</h3>
  <%-- <c:if test="${empty board}">
    <p>조회된 결과가 없습니다.</p>
    </c:if> --%>
    <form name="myFrm" action="boardDetail.do" method="post">
      <input type="hidden" name="bno" value="${board.brdNo }">
      <table border="1">
        <tr>
          <th>제목</th>
          <td><input type="text" name="title" value="${board.brdTitle }"></td>
        </tr>
        <tr>
          <th>작성자</th>
          <td><input type="text" name="writer" value="${board.brdWriter }"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td><textarea name="content" cols="30" rows="10">${board.brdContent }</textarea></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <button type="submit">수정</button>
            <button type="button">삭제</button>
          </td>
        </tr>
      </table>
    </form>
    <br>
    <hr>
    <br>
    <!-- 댓글시작 -->
    <div class="row" style="width: 85%; margin: 0 auto;">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <!-- heading -->
          <div class="panel-heading" style="height: 40px;">
            <i class="fa fa-comments fa-fw">Reply</i>
            <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">
              New Reply</button>
          </div>
          <!-- body -->
          <div class="panel-body">
            <ul class="chat">

            </ul>
          </div>
          <!-- footer -->
          <div class="panel-footer"></div>
        </div>
      </div>
    </div>
    <!-- 댓글종료 -->
    <!-- 모달창 시작 -->
    <div class="modal fade" id="myModal" style="">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- header -->
          <div class="modal-header">
            <button class="close">&times;</button>
            <h4 class="modal-title" >Reply Modal</h4>
            <input type="hidden" name = "replyNo"/>
          </div>
          <!-- body -->
          <div class="modal-body">
            <div class="form-group">
              <label for="">Reply</label> <input class="form-control" name="reply" value="New Reply">
            </div>
            <div class="form-group">
              <label for="">Replyer</label> <input class="form-control" name="replyer" value="user01">
            </div>
            <div class="form-group">
              <label for="">Reply Date</label> <input class="form-control" name="replyDate" value="2023-06-30">
            </div>
          </div>
          <!-- footer -->
          <div class="modal-footer">
            <button id="modalModBtn" class="btn btn-warning">Modify</button>
            <button id="modalRemoveBtn" class="btn btn-danger">Remove</button>
            <button id="modalRegisterBtn" class="btn btn-primary">Register</button>
            <button id="modalCloseBtn" class="btn btn-secondary">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 모달창 종료 -->

    <script>
      // 등록버튼  수정.삭제버튼 숨김
      let modal = document.querySelector('#myModal');
      document.querySelector('#addReplyBtn').addEventListener('click', function (e) {
        modal.style.display = 'flex';
        modal.style.opacity = 1;
        modal.style.justifyContent = 'center';
        modal.style.alignItems = 'center';

        document.querySelector('#modalModBtn').style.display = 'none';
        document.querySelector('#modalRemoveBtn').style.display = 'none';
        document.querySelector('#modalRegisterBtn').style.display = 'block';
        // 등록날짜 항목 숨김.
        document.querySelector('#myModal div.modal-body div.form-group:nth-Child(3)').style.display = 'none';
      })
      // 조회버튼
      function serachList() {
        document.querySelectorAll('ul.chat li').forEach(function (tag) {
          tag.addEventListener('click', function (e) {

            modal.style.display = 'flex';
            modal.style.opacity = 1;
            modal.style.justifyContent = 'center';
            modal.style.alignItems = 'center';

            document.querySelector('#modalModBtn').style.display = 'block';
            document.querySelector('#modalRemoveBtn').style.display = 'block';
            document.querySelector('#modalRegisterBtn').style.display = 'none';

            let rno = tag.dataset.rno;

            //console.log(tag.dataset.rno);

            fetch('getReply.do?rno=' + rno)
                .then( response => response.json()
                )
                .then(function (result) {
					console.log(result);
                  document.querySelector('#myModal input[name="replyNo"]').value = result.replyNo;
                  document.querySelector('#myModal input[name="reply"]').value = result.reply;
                  document.querySelector('#myModal input[name="replyer"]').value = result.replyer;
                  document.querySelector('#myModal input[name="replyDate"]').value = result.replyDate;
                })
                .catch(function (err) {
					console.log(err);
                })
          })

        })
      }
      // 창 닫기
      document.querySelector('.close').addEventListener('click', function (e) {
        modal.style.display = 'none';
        modal.style.opacity = 0;
      })
      document.querySelector('#modalCloseBtn').addEventListener('click', function (e) {
        modal.style.display = 'none';
        modal.style.opacity = 0;
      })

      function makeList(reply = {}) {
        let str = '';
        str += '<li class="left clearfix" data-rno=' + reply.replyNo + '><div><div class="header">';
        str += '<strong class="primary-font">' + reply.replyer + '</strong>';
        str += '<small class="pull-right text-muted">' + reply.replyDate + '</small></div>';
        str += '<p>' + reply.reply + '</p></div></li>';
        return str;
      }
      // 댓글목록 리스트 보여주기
      const bno = '${board.brdNo}';
      console.log(bno);
      const replyUL = document.querySelector('ul.chat');

      // 댓글목록
      function replyFnc(bno, page){
        
      	fetch('replyList.do?brdNo=' + bno + '&page=' + page)
	        .then(function (response) {
	          console.log(response);
	          return response.json();
	        })
	        .then(function (result) {
	          console.log(result);	// 페이징으로 인해 count:?? list:[] 라는 값들이 넘어온다.
            if(page == -1){
              pageNum = Math.ceil(result.count / 10.0);
              replyFnc(bno, pageNum);
              return;
            }
            replyUL.innerHTML = "";
	          for (let reply of result.list) {
	            replyUL.innerHTML += makeList(reply);
	          }
	          serachList();
	          showReplyPage(result.count);
	        })
	        .catch(function (err) {
	          console.error(err);
	        });
      	}
      	replyFnc(bno, -1);
        // 수정버튼
        document.querySelector('#modalModBtn').addEventListener('click', function(e){
          let rno = document.querySelector('#myModal input[name="replyNo"]').value;
          let reply = document.querySelector('#myModal input[name="reply"]').value;

          fetch('editReply.do',{
            method: 'post',
            headers: {
              'Content-Type':'application/x-www-form-urlencoded'
            },
            body:'rno=' + rno + '&reply=' + reply
          })
          .then(response => response.json())
          .then(result => {
            let replyNo = result.replyNo;
            let targetLI = document.querySelector('.chat li[data-rno="' + replyNo + '"]');
            targetLI.querySelector('p').innerText = result.reply;

            //modal 창 닫기
            modal.style.display = 'none';
            modal.style.opacity = 0;
          })
          .catch(err => console.log(err))
        })

        // 삭제버튼
        document.querySelector('#modalRemoveBtn').addEventListener('click', function(e){
          let rno = document.querySelector('#myModal input[name="replyNo"]').value;
          fetch('delReply.do?rno=' + rno)
          .then(function (response){
            return response.json();
          })
          .then(function (result) {
            //let targetLI = document.querySelector('.chat li[data-rno="' + rno + '"]');
            //targetLI.remove();
            replyFnc(bno, pageNum);

            //modal 창 닫기
            modal.style.display = 'none';
            modal.style.opacity = 0;
          })
          .catch(function (err) {
            console.error(err);
          });
        })
        // 등록버튼
        document.querySelector('#modalRegisterBtn').addEventListener('click', function(e){
          let replyer = document.querySelector('#myModal input[name="replyer"]').value;
          let reply = document.querySelector('#myModal input[name="reply"]').value;
          fetch('addReply.do', {
            method: 'post',
            headers:{
              'Content-Type':'application/x-www-form-urlencoded'
            },
            body: 'bno=' + bno + '&replyer=' + replyer + '&reply=' + reply
          })
          .then(response => response.json())
          .then(result => {
            //replyUL.innerHTML += makeList(result);
            //serachList();
            replyFnc(bno, -1);

            //modal 창 닫기
            modal.style.display = 'none';
            modal.style.opacity = 0;
          })
          .catch(err => console.log(err))
        })
          // 댓글갯수를 기준으로 페이지 계산
          let pageNum = 1;
          function showReplyPage(replyCnt){
            let endPage = Math.ceil(pageNum/10.0) * 10;
            let startPage = endPage - 9;
            let prev = startPage != 1;
            let next = false;

            if(endPage * 10 > replyCnt){
              endPage = Math.ceil(replyCnt/ 10.0);
            }
            if(endPage * 10 < replyCnt){
              next = true;
            }
         
            // 계산한 값으로 페이지 출력
            let str = '<ul class="pagination pull-right">';
              if(prev){
                str += '<li class="page-item"><a data-page="' + (startPage - 1) +'" href="" class="paging"> Prev </a></li>';
              }
              for(let i = startPage; i <= endPage; i++){
                str += '<li class="page-item"><a data-page="' + i +'" href="" class="paging">' + i + '</a></li>';
              }
              if(next){
                str += '<li class="page-item"><a data-page="' + (endPage + 1) +'" href="" class="paging"> Next </a></li>';
              }
              str += "</ul>";
              document.querySelector('div.panel-footer').innerHTML = str;

              // 링크클릭 이벤트
              document.querySelectorAll('a.paging').forEach(aTag => {
            	  aTag.addEventListener('click', function(e){
	            	  e.preventDefault();
	            	  pageNum = aTag.dataset.page;
	            	  replyFnc(bno, pageNum);  // 원본글, 페이지 정보사용해서 컨트롤 호출.
            	  })
              })
            }
          //showReplyPage(786);
        
    </script>