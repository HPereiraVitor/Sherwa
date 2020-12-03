create schema test;
use test;

create table player(idPlayer int auto_increment,
					nome varchar(150),
					nickName varchar(30),
                    jogo varchar(20),
                    plataforma varchar(20),
                    data_partida date,
                    qtd_pessoa int,
                    categoria varchar(30),
                    constraint pk_idPlayer primary key (idPlayer));
                    
insert into player (nome, nickName, jogo, plataforma, data_partida, qtd_pessoa, categoria)
			values ("igor","sef","sdfe","pc", sysdate(), 4, "Treino");
select * from player;

drop table player;