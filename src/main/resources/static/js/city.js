(function () {

    let cityTags = [];
    let cityComments = [];
    const cityId = document.querySelector('#cityId').textContent;

    setupPage();

    function setupPage() {
        goToPreviousButton();
        getCityTags();
        addCityTag();
        removeCityTag();
        getCityComments();
        addCityComment();
    };


    function goToPreviousButton() {
        const goToPrevious = document.querySelector('#previous');
        goToPrevious.addEventListener('click', () => window.history.back());
    };

    function getCityTags() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                cityTags = JSON.parse(xhr.response);
                cityTagListRender();
            };
        };
        xhr.open('GET', '/tags/' + cityId);
        xhr.send();
    }

    function getCityComments() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                cityComments = JSON.parse(xhr.response);
                cityCommentRender();
            };
        };
        xhr.open('GET', '/comments/' + cityId);
        xhr.send();
    }

    function addCityTag() {
        const addButton = document.querySelector('#addTagButton');
        addButton.addEventListener('click', function () {
            const userInput = prompt('What tag would you like to add?');
            if (!userInput) {
                return;
            }
        
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    cityTags = JSON.parse(xhr.response);
                    cityTagListRender();
                };
            };
            xhr.open('PUT', '/addTag/' + userInput + '/city/' + cityId);
            xhr.send();
        })
    }

    function addCityComment() {

        const submitForm = document.querySelector('#commentInput');
        submitForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const commentText = submitForm.querySelector('[name=commentText]').value;
            const cityId = submitForm.querySelector('[name=cityId]').value;
            const authorName = submitForm.querySelector('[name=authorName]').value;

            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    cityComments = JSON.parse(xhr.response);
                    submitForm.reset();
                    cityCommentRender();
                };
            };
            xhr.open('POST', `/addComment?commentText=${encodeURIComponent(commentText)}&cityId=${cityId}&authorName=${encodeURIComponent(authorName)}`);
            xhr.send();
        })
    }

    function removeCityTag() {
        const addButton = document.querySelector('#removeTagButton');
        addButton.addEventListener('click', function () {
            const userInput = prompt('What tag would you like to remove?');
            if (!userInput) {
                return;
            }

            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    cityTags = JSON.parse(xhr.response);
                    cityTagListRender();
                };
            };
            xhr.open('PUT', '/removeTag/' + userInput + '/city/' + cityId);
            xhr.send();
        })
    }

    function cityTagListRender() {

        //Get container and clear it
        const tagListSection = document.querySelector('#cityTags');
        tagListSection.innerHTML = '';

        //Add element and Tags content
        if (!cityTags.length) {
            let newTagDiv = document.createElement('div');
            let newTag = document.createElement('span');
            tagList.innerHTML += 'No current tags';
        }
        else {
            cityTags.forEach(tag => {
                let newTagDiv = document.createElement('div');
                let newTag = document.createElement('span');
                newTag.innerHTML = tag.name;
                newTagDiv.appendChild(newTag);
                tagListSection.appendChild(newTagDiv);
            })
        }
    }

    function cityCommentRender() {

        //Get container and clear it
        const commentContainer = document.querySelector('#commentsContainer');
        commentContainer.innerHTML = '';

        //Add element and Tags content
        if (!cityComments.length) {
            let newCommentDiv = document.createElement('div');
            newCommentDiv.setAttribute('class', 'commentsDiv');
            let newCommentText = document.createElement('span');
            newCommentText.textContent = 'No current comments';
            newCommentDiv.appendChild(newCommentText);
            commentContainer.appendChild(newCommentDiv);
        }
        else {
            cityComments.forEach(comment => {
                let newCommentDiv = document.createElement('div');
                newCommentDiv.setAttribute('class', 'commentsDiv');
                let newCommentText = document.createElement('span');
                newCommentText.textContent = comment.commentText;
                newCommentDiv.appendChild(newCommentText);
                
                let commentAuthorDiv = document.createElement('div');
                commentAuthorDiv.setAttribute('id', 'commentAuthor');
                let newCommentAuthor = document.createElement('span');
                newCommentAuthor.textContent = '- ' + comment.authorName;
                commentAuthorDiv.appendChild(newCommentAuthor);
                newCommentDiv.appendChild(commentAuthorDiv);
                commentContainer.appendChild(newCommentDiv);
            })
        }
    }

})();