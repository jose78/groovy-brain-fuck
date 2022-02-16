FROM groovy:3.0.9-jdk11-alpine

#Labels as key value pair
LABEL Maintainer="roushan.me17"

# Any working directory can be chosen as per choice like '/' or '/home' etc
# i have chosen /usr/app/src


#to COPY the remote file at working directory in container
COPY main.groovy ./
COPY csv csv




#CMD instruction should be used to run the software
#contained by your image, along with any arguments.
CMD [ "groovy", "./main.groovy"]

