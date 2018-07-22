(function () {

    let allTags = [];

    setupPage();

    function setupPage() {
        getAllTags();
        removeOneTag();
    };

    function getAllTags() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                allTags = JSON.parse(xhr.response);
                allTagListRender();
            }
        };
        xhr.open('GET', '/tags');
        xhr.send();
    }

    function removeOneTag() {
        const addButton = document.querySelector('#removeAllTagButton');
        addButton.addEventListener('click', function () {
            const userInput = prompt('What tag would you like to remove?');
            if (!userInput) {
                return;
            }

            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    allTags = JSON.parse(xhr.response);
                    allTagListRender();
                };
            };
            xhr.open('PUT', '/removeOneTag/' + userInput);
            xhr.send();
        })
    }

    function allTagListRender() {

        //Get container and clear it
        const tagListSection = document.querySelector('#tagList');
        tagListSection.innerHTML = '';

        //Add element and Tags content
        if (!allTags.length) {
            return;
        }
        else {
            allTags.forEach(tag => {
                let newTagDiv = document.createElement('div');
                let newTag = document.createElement('a');
                let tagHref = '/tag?id=' + tag.id;
                newTag.setAttribute('href', tagHref);
                newTag.innerHTML = tag.name;
                newTagDiv.appendChild(newTag);
                tagListSection.appendChild(newTagDiv);
            })
        }
    }

})();