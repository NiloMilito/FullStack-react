import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import './update.css';
import api from '../../service/service';

class EditarUsuario extends Component {
    constructor(props) {
        super(props);

        this.state = {
            usuario: {
                nome: "",
                matricula: 0
            },
            redirect: false
        }
    }

    async componentDidMount() {
        const {id} = this.props.match.params;
        const response = await api.get(`/usuarios/${id}`);
        this.setState({usuario: response.data});
    }

    render() {
        const {redirect} = this.state;
        if(redirect) {
            return <Redirect to="/" />
        } else {
            return (
                <form onSubmit={this.send}>
                    <fieldset>
                    <legend>Editar Usuário</legend>
                    <div className="usuario-update">
                        <label htmlFor="nome">Nome</label>
                        <br/>
                        <input type="text" id="nome" placeholder="Informe seu nome" 
                         minLength="3" maxLength="100" value={this.state.usuario.nome}
                          />
                    </div>

                    <div className="usuario-update">
                        <label htmlFor="matricula">Matricula</label>
                        <br/>
                        <input type="number" id="matricula" placeholder="Informe sua matricula" 
                         minLength="1" maxLength="9999" value={this.state.usuario.matricula}
                         />
                         
                    </div>
                    <div className="usuario-update">
                        <label>
                            <input type="radio" name="ativo" value="true" 
                            checked={this.state.usuario.ativo === "true"}
                            />
                            Ativo
                        </label>
                        <label>
                            <input type="radio" name="inativo" value="false" 
                            checked={this.state.usuario.ativo === "false"}
                            />
                            Inativo
                        </label>
                    </div>

                    <button type="submit">Editar</button>
                    </fieldset>
                </form>
            )
        }  
    }
    
    inputChange = event => {
        const target = event.target;
        const name = target.name;
        const value = target.value;

        this.setState(prevState => ({
            usuario: {...prevState.usuario, [name]: value}
        }));
    };

    send = event => {
        const {id} = this.props.match.params;
        fetch(`http://localhost:3001/sistema/usuarios/${id}`, {
            method:"put",
            id: id,
            body: JSON.stringify(this.state.usuario),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(data => {
            if(data.ok) {
                this.setState({redirect: true});
            }
        })
        event.preventDefault();
    }
}

export default EditarUsuario;
