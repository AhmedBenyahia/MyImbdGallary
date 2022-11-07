# MyImbdGallary
This project use simple version of IMDB database to create some APIs to search for movies and actors.
to view the list of APIs see the swagger file at the root folder.

The dataset was downloaded from this website: https://www.imdb.com/interfaces/
We have important there files:
- title.basics.tsv.gz list of movies, shows, short movies etc
- name.basics.tsv.gz – list of actors, actress, directors etc
- title.principals.tsv.gz – relation between persons and titles

The files was loaded into MySQL databse using the following command:
`# Load data from file to table
LOAD DATA LOCAL INFILE '/home/sayto/Desktop/Projects/TechTestArelion/title-principle.tsv'
    into table movie_cast FIELDS TERMINATED BY '\t' ENCLOSED BY '' LINES TERMINATED BY '\n' IGNORE 1 LINES;`
    
 Some modification and cleaning was performed on the data.
