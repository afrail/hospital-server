###=====================================CONFIGURATION=====================================
SERVICE_NAME="customer-service"
DEPLOYABLE_BRANCH="develop"
###=======================================================================================
ROOT_DIR=$(pwd)
SERVICE_DIRECTORY="${ROOT_DIR}/${SERVICE_NAME}"
SERVICE_TARGET_DIRECTORY="${ROOT_DIR}/${SERVICE_NAME}/target/"
GIT_URL="git@gitlab.com:webx-global/${SERVICE_NAME}.git"
###=======================================================================================
echo "---you are currently @ ${ROOT_DIR}, checking required files..."
if [ ! -f "Dockerfile" ]; then
        echo "$(tput setaf 1)---error::file missing, please create your Dockerfile first."
        exit 1
fi
if [ ! -f "docker-compose.yml" ]; then
        echo "$(tput setaf 1)---error::file missing, please create your docker-compose.yml."
        exit 1
fi
echo "---searching for service '${SERVICE_NAME}'"
if [ -d "${SERVICE_DIRECTORY}" ]; then
    echo "---found, entering into the directory..."
    cd ${SERVICE_NAME}
    CURRENT_BRANCH=$(git branch --show-current)
    echo "---your current branch is '${CURRENT_BRANCH}'"
    if [[ "${CURRENT_BRANCH}" != "${DEPLOYABLE_BRANCH}" ]]; then
                echo "---switching branch to '${DEPLOYABLE_BRANCH}'"
                git checkout ${DEPLOYABLE_BRANCH}
    fi
else
  echo "---not found, cloning service from git..." 
  git clone ${GIT_URL}
  echo "---successfully cloned, entering into the directory..."
  cd ${SERVICE_NAME}
  echo "---switching branch to '${DEPLOYABLE_BRANCH}'"
  git checkout ${DEPLOYABLE_BRANCH}
fi

echo "---updating '${SERVICE_NAME}' from git"
git pull
echo "---build '${SERVICE_NAME}'"
mvn clean install
if [[ "$?" -ne 0 ]] ; then
  echo "$(tput setaf 1)---failed to build ${SERVICE_NAME}";
  exit 1
fi
if [ ! -d "${SERVICE_TARGET_DIRECTORY}" ]; then
        echo "$(tput setaf 1)---error::directory missing, please build ${SERVICE_NAME} first."
        exit 1
fi

cd ${ROOT_DIR}
echo "---shutdown docker"
sudo docker-compose down
echo "---removing 'app.jar' file from ${ROOT_DIR}"
rm -rf ${ROOT_DIR}/app.jar
echo "---copy newly build 'app.jar' from ${SERVICE_TARGET_DIRECTORY} to ${ROOT_DIR}"
cp ${SERVICE_TARGET_DIRECTORY}/app.jar ${ROOT_DIR}
echo "---make alive docker"
sudo docker-compose up --build
exit 1

