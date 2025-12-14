--changeset Volkov Pavel:1
create table if not exists chats (
    chat_id serial primary key ,
    chat_name text not null
);

--changeset Volkov Pavel:2
create table if not exists messages (
    message_id bigserial primary key ,
    sender_id int not null references users(user_id) ,
    receiver_chat_id int references chats(chat_id) on delete cascade ,
    message text not null ,
    date timestamp not null
);

--changeset Volkov Pavel:3
create table chats_users (
    chat_id int not null ,
    user_id int not null ,
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    PRIMARY KEY (chat_id, user_id)
);

