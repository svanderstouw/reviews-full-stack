(function () {

    let cityTags = [];
    const cityId = document.querySelector('#cityId').textContent;

    setupPage();

    function setupPage() {
        goToPreviousButton();
        getCityTags();
        addCityTag();
        removeCityTag();
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
                cityTags = JSON.parse(xhr.response);
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

})();