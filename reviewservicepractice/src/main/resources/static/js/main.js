document.addEventListener("DOMContentLoaded", function() {
document.getElementById("analysisRequest").addEventListener("submit", function(event){
    event.preventDefault(); // 기본 폼 제출 동작을 막음

    const dayValue = document.getElementById("dayInput").value;

    // 비동기적으로 서버에 POST 요청을 보냄
    fetch("/review-analysis", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            "day": dayValue
        })
    })
    .then(response => {
        if (response.ok) {
            alert("분석이 완료되었습니다.");
        } else {
            alert("분석에 실패했습니다.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("요청 중 오류가 발생했습니다.");
    });
});
});

function viewReview(reviewNo) {
    fetch(`/reviews/${reviewNo}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("리뷰를 찾을 수 없습니다.");
            }
            return response.json();
        })
        .then(data => {
            // 리뷰 데이터 추출
            const review = data.reivew;
            const images = data.images || [];

            // 제목과 내용 업데이트
            document.getElementById("viewTitle").textContent = review.reviewTitle;
            document.getElementById("viewContent").textContent = review.reviewContent;

            // 이미지 슬라이더 초기화
            const viewImages = document.getElementById("viewImages");
            viewImages.innerHTML = ''; // 기존 이미지를 제거합니다.

            images.forEach(image => {
                const img = document.createElement("img");
                img.src = `/reviewImage/${image.imageName}`; // 예: /reviewImage/mudo2.webp
                img.alt = `${image.imageName}`;
                img.addEventListener("click", () => {
                    openImageModal(img.src);
                });
                viewImages.appendChild(img);
            });

            document.getElementById("viewPopup").style.display = "flex";
        })
        .catch(error => {
            alert(error.message);
        });
}

function closeViewPopup() {
    document.getElementById("viewPopup").style.display = "none";
}

// 이미지 확대 모달
function openImageModal(imageUrl) {
    const imageModal = document.createElement("div");
    imageModal.style.position = "fixed";
    imageModal.style.top = "0";
    imageModal.style.left = "0";
    imageModal.style.width = "100%";
    imageModal.style.height = "100%";
    imageModal.style.backgroundColor = "rgba(0, 0, 0, 0.8)";
    imageModal.style.display = "flex";
    imageModal.style.justifyContent = "center";
    imageModal.style.alignItems = "center";
    imageModal.style.zIndex = "1001";
    imageModal.innerHTML = `
        <img src="${imageUrl}" style="max-width: 90%; max-height: 90%;">
    `;
    imageModal.addEventListener("click", () => {
        document.body.removeChild(imageModal);
    });
    document.body.appendChild(imageModal);
}

// 리뷰 작성 팝업 열기
function openPostPopup() {
    document.getElementById("postPopup").style.display = "flex";
}

// 리뷰 작성 팝업 닫기
function closePostPopup() {
    document.getElementById("postPopup").style.display = "none";
}


window.addEventListener('DOMContentLoaded', event => {
 // 검색 버튼 클릭 이벤트 핸들러
    document.getElementById("btnNavbarSearch").addEventListener("click", function() {
        const searchKeyword = document.getElementById("searchInput").value;

        fetch(`/reviews/search/${encodeURIComponent(searchKeyword)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("검색 결과를 가져올 수 없습니다.");
                }
                return response.json();
            })
            .then(reviews => {
                // 리뷰 테이블 업데이트
                const tbody = document.querySelector("#datatablesSimple tbody");
                tbody.innerHTML = ""; // 기존 테이블 내용을 지웁니다.

                if (reviews.length === 0) {
                    // 검색 결과가 없는 경우: 테이블이 비어 있는 상태로 유지
                    const tr = document.createElement("tr");
                    const td = document.createElement("td");
                    td.setAttribute("colspan", "4");
                    td.textContent = "검색 결과가 없습니다.";
                    td.style.textAlign = "center";
                    tr.appendChild(td);
                    tbody.appendChild(tr);
                } else {
                    reviews.forEach(review => {
                        const tr = document.createElement("tr");

                        const tdReviewer = document.createElement("td");
                        tdReviewer.textContent = review.reviewerId;
                        tr.appendChild(tdReviewer);

                        const tdTitle = document.createElement("td");
                        const titleLink = document.createElement("a");
                        titleLink.href = "#";
                        titleLink.textContent = review.reviewTitle;
                        titleLink.setAttribute("onclick", `viewReview(${review.reviewNo})`);
                        tdTitle.appendChild(titleLink);
                        tr.appendChild(tdTitle);

                        const tdContent = document.createElement("td");
                        tdContent.textContent = review.reviewContent;
                        tr.appendChild(tdContent);

                        const tdDate = document.createElement("td");
                        tdDate.textContent = review.reviewDate;
                        tr.appendChild(tdDate);

                        tbody.appendChild(tr);
                    });
                }
            })
            .catch(error => {
                alert(error.message);
            });
    });
});



// 리뷰 작성 폼 제출 이벤트 핸들러
document.getElementById("saveReview").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 방지합니다.

    const formData = new FormData(this); // 폼 데이터를 FormData 객체로 생성

    fetch("/review/saveReview", {
        method: "POST",
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("리뷰를 등록하는 중 오류가 발생했습니다.");
        }
        return response.text();
    })
    .then(data => {
        if (data === "success") {
            alert("정상 등록되었습니다.");
            closePostPopup(); // 팝업을 닫습니다.
            window.location.href = "/main"; // /main 경로로 새로고침
        } else {
            alert("등록 오류가 발생했습니다. 다시 시도해 주세요.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert(error.message);
    });
});


