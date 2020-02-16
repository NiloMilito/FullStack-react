

const mongoose = require('mongoose');

const mongoosePaginate = require('mongoose-paginate');


const UserSchema = new mongoose.Schema({
    nome: {
        type: String,
        required: true,
        uppercase: true,
        minlength: 5,
        maxlength: 100
    },
    matricula: {
        type: Number,
        required: true,
        min: 1,
        max: 9999,
        unique: true
    },
    ativo: {
        type: Boolean,
        default: true,
        required: true
    },
    registro: {
        type: Date,
        default: Date.now
    }
});

UserSchema.plugin(mongoosePaginate);

mongoose.model('Usuario', UserSchema);