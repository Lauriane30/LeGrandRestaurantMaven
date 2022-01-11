git fetch https://github.com/Lauriane30/LeGrandRestaurantMaven.git

git branch deploy

git checkout deploy

mvn package

git add . && git commit -m "Commit on deploy branch" && git push