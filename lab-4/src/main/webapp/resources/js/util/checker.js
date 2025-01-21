function checker(valuesX, valueY, valueR) {
    if (!Array.isArray(valuesX)) {
        valuesX = [valuesX];
    }

    if (valuesX.length === 0) throw new NotChooseValueError('x', 'Выберите Х');
    if (valueY === null || valueY === undefined || isNaN(valueY)) {
        throw new NotChooseValueError('y', 'Поле ввода Y пустое, пожалуйста, введите значение в диапазоне от -3 до 3');
    }
    if (valueY < -3 || valueY > 3) throw new NotChooseValueError('y', 'Y должен быть в диапазоне от -3 до 3(((');
    if (!valueR) throw new NotChooseValueError('r', 'Выберите R');

    return true;
}

