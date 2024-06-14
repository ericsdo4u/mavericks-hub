truncate table users cascade;
truncate table media cascade;

insert into users(id, email, password, time_created) values
(200, 'jamey@gmail.com','password','2024-06-04T15:03:03.792009700'),
(201, 'jane@gmail.com','password','2024-06-04T15:03:03.792009700'),
(202, 'johnny@gmail.com','password','2024-06-04T15:03:03.792009700'),
(203, 'james@gmail.com','password','2024-06-04T15:03:03.792009700');

insert into media (id, category, description, url, time_created, uploader_id) values
(100, 'ACTION', 'media 1', 'https://www.cloudinary.com/medai1', '2024-06-04T15:03:03.792009700', 200),
(101, 'STEP_MOM', 'media 2', 'https://www.cloudinary.com/medai2', '2024-06-04T15:03:03.792009700', 200),
(102, 'COMEDY', 'media 3', 'https://www.cloudinary.com/medai3', '2024-06-04T15:03:03.792009700', 201),
(103, 'ROMANCE', 'media 4', 'https://www.cloudinary.com/medai4', '2024-06-04T15:03:03.792009700', 200),
(104, 'DRAMA', 'media 5', 'https://www.cloudinary.com/medai5', '2024-06-04T15:03:03.792009700', 201);

insert into playlists(id, name, description, time_created, uploader_id) values
(300, 'name 1', 'description', '2024-06-04T15:03:28.739603300', 201),
(301, 'name 2', 'description1', '2024-06-04T15:03:28.739603300', 200),
(302, 'name 3', 'description2', '2024-06-04T15:03:28.739603300', 202);

insert into playlist_media(playlist_id, media_id) VALUES
                                 (300, 100),
                                 (300, 101),
                                (300, 103),
                                (301, 102),
                                (301, 104);
